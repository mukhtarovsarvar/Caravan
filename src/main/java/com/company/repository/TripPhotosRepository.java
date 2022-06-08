package com.company.repository;

import com.company.entity.TripPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripPhotosRepository extends JpaRepository<TripPhotoEntity, String> {
}