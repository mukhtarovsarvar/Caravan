package com.company.controller;

import com.company.dto.profile.ProfileDTO;
import com.company.dto.profile.ProfileDetailDTO;
import com.company.dto.response.LoginResponse;
import com.company.dto.response.RegisterResponse;
import com.company.service.AuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Api(tags = "Authorization")
public class AuthorizationController {

    private final AuthorizationService authorizationService;


    @ApiOperation(value = "Send Sms", notes = "Method used for send sms")
    @PostMapping("/send")
    public ResponseEntity<LoginResponse> sendSms(@RequestBody @Valid ProfileDetailDTO dto) {
        log.info("Send Sms {}", dto);
        return authorizationService.sendSms(dto);
    }

    @ApiOperation(value = "Login", notes = "Method used for login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid ProfileDetailDTO dto) {
        log.info("Login {}", dto);
        return authorizationService.login(dto);
    }

    @ApiOperation(value = "Registration", notes = "Method used for registration")
    @PostMapping("/registration")
    public ResponseEntity<RegisterResponse> registration(@RequestBody @Valid ProfileDTO dto) {
        log.info("Registration {}", dto);
        return authorizationService.registration(dto);
    }

//    @ApiOperation(value = "Delete", notes = "Method used for delete profile")
//    @DeleteMapping("/delete")
//    public ResponseEntity<?> delete(UserDetails userDetails) {
//        log.info("Delete {}", userDetails.getUsername());
//        return authorizationService.delete(userDetails.getUsername());
//    }
}
