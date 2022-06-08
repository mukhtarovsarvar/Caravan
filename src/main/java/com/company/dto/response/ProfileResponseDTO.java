package com.company.dto.response;

import com.company.dto.DeviceDTO;
import com.company.enums.AppLang;
import com.company.enums.Gender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Setter
@Getter
public class ProfileResponseDTO {

    private String id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private ProfileRole role;

    private String  guideId;

    private ProfileStatus status;

    private String  profilePhoto;

    private Gender gender;

    private LocalDate birthDate;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private AppLang appLanguage;

    private ArrayList<DeviceDTO> devices;
}
