package com.company.dto.profile;

import com.company.dto.BaseDTO;
import com.company.dto.DeviceDTO;
import com.company.enums.AppLang;
import com.company.enums.Gender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProfileDTO extends BaseDTO {

    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Surname required")
    private String surname;

    @NotBlank(message = "PhoneNumber required")
    private String phoneNumber;

    private String email;

    private ProfileRole role;

    private ProfileStatus status;

    private String photo;

    @NotNull(message = "Gender required")
    private Gender gender;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String birthDate;

    private AppLang appLanguage;

    private List<DeviceDTO> devices;

    private String guideId;

    private String token;

    public ProfileDTO(String id, String name, String surname, String photo) {
        super.id = id;
        this.name = name;
        this.surname = surname;
        this.photo = photo;
    }
}
