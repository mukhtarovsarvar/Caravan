package com.company.mapper;

import com.company.entity.GuideEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.AppLang;
import com.company.enums.Gender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface GuideProfileInfoMapper {

    String getG_id();

    String getP_id();
    String getP_name();
    String getP_surname();
    String getP_phone_number();
    String getP_email();
    ProfileRole getP_role();
    ProfileStatus getP_status();
    String getP_photo();
    Gender getP_gender();
    String getP_birth_date();
    LocalDateTime getP_created_date();
    LocalDateTime getP_updated_date();
    AppLang getP_app_language();

}
