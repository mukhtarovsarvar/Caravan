package com.company.entity;

import com.company.enums.AppLang;
import com.company.enums.Gender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "profile", uniqueConstraints = @UniqueConstraint(columnNames = {"phone_number", "deleted_date"}))
@Getter
@Setter
public class ProfileEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "photo")
    private String photo;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private String birthDate;

    @Column
    @Enumerated(EnumType.STRING)
    private AppLang appLanguage;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

}
