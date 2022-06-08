package com.company.repository;

import com.company.dto.GuideFilterDTO;
import com.company.entity.GuideEntity;

import com.company.enums.ProfileRole;
import com.company.mapper.GuideRateMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GuideRepository extends JpaRepository<GuideEntity, String> {

    Optional<GuideEntity> findByProfileId(String pId);

    Optional<GuideEntity> findByProfile_PhoneNumber(String phone);

    @Modifying
    @Transactional
    @Query("update GuideEntity as g set g.isHiring =:isHiring where g.profile.phoneNumber = :phone")
    void updateIsHiring(@Param("phone") String phone, @Param("isHiring") Boolean isHiring);


    @Query("select g.id as guideId,g.profileId as profileId," +
            "g.phoneNumber as phone,g.biography as bio," +
            "g.isHiring as hiring, g.priceId as priceId, p.cost as price_cost, p.currency as price_currency, p.type as price_type," +
            "g.languageList as languge,g.locationList as locations," +
            "g.profile.photo as photo,g.createdDate as createDate, avg (r.rate) as rate" +
            " from ReviewEntity as r " +
            " inner join r.guide as g " +
            " inner join g.price as p " +
            " where g.profile.role = :role " +
            " group by g.id,g.profileId ," +
            " g.phoneNumber ,g.biography ," +
            " g.isHiring , g.priceId ," +
            " g.languageList ,g.locationList  , " +
            "g.profile.photo ")
    List<GuideRateMapper> getRate(Pageable pageable, @Param("role") ProfileRole role);


    @Modifying
    @Transactional
    @Query("update GuideEntity  set  deletedDate = ?2 where profileId =?1 and  deletedDate is null ")
    void updateDeleteDate(String id, LocalDateTime now);

}