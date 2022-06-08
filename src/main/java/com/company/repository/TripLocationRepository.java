package com.company.repository;

import com.company.entity.TripLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripLocationRepository extends JpaRepository<TripLocationEntity, String> {
}