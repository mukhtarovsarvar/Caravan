package com.company.repository;

import com.company.entity.ProfileDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetailEntity, String> {

    Optional<ProfileDetailEntity> findByPhoneNumber(String phone);

}