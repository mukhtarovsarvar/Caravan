package com.company.entity;

import com.company.enums.AppLang;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "profile_detail")
@Getter
@Setter
public class ProfileDetailEntity extends BaseEntity {

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "sms_code")
    private String smsCode;

    @Column(name = "app_language")
    @Enumerated(EnumType.STRING)
    private AppLang appLanguage;


}
