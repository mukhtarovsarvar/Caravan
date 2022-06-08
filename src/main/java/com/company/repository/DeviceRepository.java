package com.company.repository;

import com.company.entity.DeviceEntity;
import com.company.mapper.ProfileDeviceInfoMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceEntity, String> {

    @Query(value = "select d.deviceId as d_id, d.deviceToken as d_token, d.deviceType as d_type " +
            "from DeviceEntity d " +
            "inner join d.profileDetail p " +
            "where p.phoneNumber = :phoneNumber " +
            "order by d.createdDate desc")
    List<ProfileDeviceInfoMapper> findAllDeviceByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<DeviceEntity> findByDeviceIdAndDeviceTokenAndProfileDetailId(String deviceId, String deviceToken, String profileDetailId);
}