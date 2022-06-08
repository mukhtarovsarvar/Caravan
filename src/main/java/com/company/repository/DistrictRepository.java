package com.company.repository;

import com.company.entity.DistrictEntity;
import com.company.enums.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<DistrictEntity,Integer> {

    List<DistrictEntity> findAllByRegion(Region region);

}