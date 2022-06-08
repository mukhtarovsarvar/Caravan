package com.company.service;

import com.company.dto.profile.ProfileDetailDTO;
import com.company.entity.ProfileDetailEntity;
import com.company.repository.ProfileDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileDetailService {

    private final ProfileDetailRepository profileDetailRepository;


    public void create(ProfileDetailDTO dto) {
        ProfileDetailEntity entity = getByPhoneNumber(dto.getPhoneNumber());

        if (Optional.ofNullable(entity).isEmpty()) {
            entity = new ProfileDetailEntity();
        }

        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setSmsCode(dto.getSmsCode());
        entity.setAppLanguage(dto.getAppLanguage());
        entity.setUpdatedDate(LocalDateTime.now());

        profileDetailRepository.save(entity);
    }

    public ProfileDetailEntity getByPhoneNumber(String phoneNumber) {
        return profileDetailRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);

    }

    public void delete(String id) {
        ProfileDetailEntity profileDetailEntity = profileDetailRepository.findById(id).orElse(new ProfileDetailEntity());
        profileDetailRepository.delete(profileDetailEntity);
    }
}
