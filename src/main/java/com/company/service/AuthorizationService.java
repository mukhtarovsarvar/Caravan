package com.company.service;

import com.company.dto.profile.ProfileDTO;
import com.company.dto.profile.ProfileDetailDTO;
import com.company.dto.response.ActionDTO;
import com.company.entity.ProfileDetailEntity;
import com.company.mapper.GuideProfileInfoMapper;
import com.company.dto.response.LoginResponse;
import com.company.dto.response.RegisterResponse;
import com.company.util.JwtUtil;
import com.company.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final ProfileService profileService;

    private final GuideService guideService;

    private final DeviceService deviceService;

    private final ProfileDetailService profileDetailService;

    @Value("${message.access.key}")
    private String ACCESS_KEY;

    public ResponseEntity<LoginResponse> sendSms(ProfileDetailDTO dto) {
        String code = RandomUtil.getRandomSmsCode();
        System.out.println(code);

        dto.setSmsCode(code);
        dto.setSms("Your verification code is " + code);

        try {
            sendMessage(dto);
        } catch (RuntimeException e) {
            log.warn("Bad request {}", dto);
            return response(dto, new LoginResponse());
        }
        profileDetailService.create(dto);

        return ResponseEntity.ok().body(new LoginResponse(true, null, String.valueOf(code)));
    }

    private void sendMessage(ProfileDetailDTO dto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recipients", dto.getPhoneNumber());
        jsonObject.put("originator", "Caravan");
        jsonObject.put("body", dto.getSms());

        RequestBody formBody = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://rest.messagebird.com/messages")
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "AccessKey " + ACCESS_KEY)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        Thread thread = new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    System.out.println(response);
                } else {
                    throw new RuntimeException();
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (th, ex) -> {

        };

        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        thread.start();

    }

    public ResponseEntity<LoginResponse> login(ProfileDetailDTO dto) {

        ProfileDetailEntity entity = profileDetailService.getByPhoneNumber(dto.getPhoneNumber());

        GuideProfileInfoMapper mapper = profileService.getByPhoneNumberMapper(dto.getPhoneNumber());

        if (Optional.ofNullable(entity).isEmpty() ||
                !entity.getSmsCode().equals(dto.getSmsCode()) ||
                LocalDateTime.now().isAfter(entity.getUpdatedDate().plusSeconds(60))) {
            log.warn("Bad request {}", dto);
            return response(dto, new LoginResponse(false,false,null,null));
        }

        deviceService.create(dto.getDevice(), entity.getId());

        if (Optional.ofNullable(mapper).isPresent()) {
            ProfileDTO profileDTO = profileService.toDTOMapper(mapper);

            profileDTO.setToken(JwtUtil.encode(profileDTO.getPhoneNumber()));


            switch (profileDTO.getRole()) {
                case ROLE_TOURIST -> {
                    return ResponseEntity.ok().body(
                            new LoginResponse(true, false, profileDTO, null));
                }
                case ROLE_GUIDE -> {
                    return ResponseEntity.ok().body(
                            new LoginResponse(true, true, profileDTO, profileDTO.getGuideId()));
                }
            }
        }

        return ResponseEntity.ok().body(new LoginResponse(false, false, null, null));
    }

    /*    public ResponseEntity<RegisterResponse> registration(ProfileDTO dto) {
            RegisterResponse response = new RegisterResponse();

            ProfileDetailEntity entity = profileDetailService.getByPhoneNumber(dto.getPhoneNumber());

            if (Optional.ofNullable(entity).isEmpty()) {
                response.setMessage("You're not login!");
                return ResponseEntity.badRequest().body(response);
            }
            if (Optional.ofNullable(profileService.getByPhoneNumber(dto.getPhoneNumber())).isPresent()) {
                response.setMessage("This phone number already used!");
                return ResponseEntity.badRequest().body(response);
            }

            return ResponseEntity.ok(new RegisterResponse(true));
        }*/
    public ResponseEntity<RegisterResponse> registration(ProfileDTO dto) {
        RegisterResponse response = new RegisterResponse();

        ProfileDetailEntity entity = profileDetailService.getByPhoneNumber(dto.getPhoneNumber());


        if (Optional.ofNullable(entity).isEmpty()) {
            response.setMessage("You're not login!");
            return ResponseEntity.badRequest().body(response);
        }
        if (Optional.ofNullable(profileService.getByPhoneNumber(dto.getPhoneNumber())).isPresent()) {
            response.setMessage("This phone number already used!");
            return ResponseEntity.badRequest().body(response);
        }

        dto.setAppLanguage(entity.getAppLanguage());
        ActionDTO action = profileService.create(dto);
        if (!action.getStatus().equals(true)) {
            response.setMessage(action.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        var profileEntity = profileService.getByPhoneNumber(dto.getPhoneNumber());
        var profile = profileService.toDTO(profileEntity);
        profile.setToken(JwtUtil.encode(profile.getPhoneNumber()));

        return ResponseEntity.ok(new RegisterResponse(true, profile));
    }

    public ResponseEntity<LoginResponse> response(ProfileDetailDTO dto, LoginResponse response) {

        switch (dto.getAppLanguage()) {
            case en -> {
                response.setTitle("Sms code incorrect");
                response.setMessage("You entered wrong code. Please try again. If the code didn't come. Please contact us.");
            }
            case uz -> {
                response.setTitle("Sms kod xato terilgan");
                response.setMessage("Siz xato kod terdingiz. Iltimos qaytadan urinib ko'ring. Agar kod kelmagan bo'lsa. Biz bilan bog'laning.");
            }
            case ru -> {
                response.setTitle("Не правильный смс код");
                response.setMessage("Вы ввели не правильный смс код. Пожалуйста повторите ещё раз. Если код не пришёл. Свяжитесь с нами.");
            }
        }
        return ResponseEntity.badRequest().body(response);
    }

/*    public ResponseEntity<?> delete(Long id) {
        profileService.delete(id);
        return ResponseEntity.ok().build();
    }*/

    public ResponseEntity<?> delete(String id) {
        profileDetailService.delete(id);
        return ResponseEntity.ok().build();
    }
}
