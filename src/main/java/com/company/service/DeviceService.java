package com.company.service;

import com.company.dto.DeviceDTO;
import com.company.entity.DeviceEntity;
import com.company.mapper.ProfileDeviceInfoMapper;
import com.company.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;


    public DeviceDTO create(DeviceDTO dto, String profileId) {
        DeviceEntity entity = findByDeviceIdAndDeviceTokenAndProfileDetailId(dto.getDeviceId(), dto.getDeviceToken(), profileId);

        if (Optional.ofNullable(entity).isEmpty()) {
            entity = new DeviceEntity();
        }

        entity.setDeviceId(dto.getDeviceId());
        entity.setDeviceToken(dto.getDeviceToken());
        entity.setDeviceType(dto.getDeviceType());
        entity.setProfileDetailId(profileId);
        entity.setUpdatedDate(LocalDateTime.now());
        deviceRepository.save(entity);
        return toDTO(entity);
    }

    public DeviceEntity findByDeviceIdAndDeviceTokenAndProfileDetailId(String deviceId, String deviceToken, String profileId) {
        return deviceRepository
                .findByDeviceIdAndDeviceTokenAndProfileDetailId(deviceId, deviceToken, profileId)
                .orElse(null);
    }

    public DeviceDTO toDTO(DeviceEntity entity) {
        DeviceDTO dto = new DeviceDTO();
        dto.setDeviceId(entity.getDeviceId());
        dto.setDeviceToken(entity.getDeviceToken());
        dto.setDeviceType(entity.getDeviceType());
        return dto;
    }

    public DeviceDTO toDTOMapper(ProfileDeviceInfoMapper mapper) {
        DeviceDTO dto = new DeviceDTO();
        dto.setDeviceId(mapper.getD_id());
        dto.setDeviceToken(mapper.getD_token());
        dto.setDeviceType(mapper.getD_type());
        return dto;
    }

    public List<DeviceDTO> devicesByProfile(String phoneNumber) {
        return deviceRepository
                .findAllDeviceByPhoneNumber(phoneNumber)
                .stream()
                .map(this::toDTOMapper)
                .toList();
    }


}
