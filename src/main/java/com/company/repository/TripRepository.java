package com.company.repository;

import com.company.entity.TripEntity;
import com.company.mapper.TripRateMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, String> {


    @Modifying
    @Transactional
    @Query(value = "update trip set price_id = :priceId, facility_list = :facility where id = :id",
            nativeQuery = true)
    void updateDetail(@Param("priceId") String price,
                      @Param("id") String id,
                      @Param("facility") List<String> facility);


    Page<TripEntity> findByGuide_Profile_PhoneNumber(String phone, Pageable pageable);


    @Query(value = "SELECT  t.id as id, t.name as title, t.description as description," +
            "t.priceId as price_id, p.cost as price_cost, p.currency as price_currency, p.type as price_type, " +
            "t.minPeople as min_people," +
            "t.maxPeople as max_people, t.guideId as guide_id," +
            "t.phoneNumber as phone," +
            "t.places as places," +
            "t.createdDate as created_date, avg (r.rate) as rate " +
            " FROM  ReviewEntity AS r " +
            "inner join TripEntity t on r.tripId = t.id " +
            "inner join t.price p " +
            "where t.priceId is not null " +
            "group by t.id, t.name, t.description, t.priceId, " +
            "t.minPeople, t.maxPeople, t.guideId, t.phoneNumber, " +
            " t.places, t.createdDate ")
    Page<TripRateMapper> getRate(Pageable pageable);

    //t.facility_list as facilitys,
}


