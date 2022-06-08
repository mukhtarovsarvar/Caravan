package com.company.service;

import com.company.dto.LocationDTO;
import com.company.entity.LocationEntity;
import com.company.exception.ItemNotFoundException;
import com.company.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationDTO create(LocationDTO dto) {
        LocationEntity entity = new LocationEntity();

        entity.setDescription(dto.getDescription());
        entity.setDistrict(dto.getDistrict());
        entity.setProvence(dto.getProvence());

        locationRepository.save(entity);

        return toDTO(entity);
    }


    public Boolean delete(String id) {
        locationRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("location not found!");
        });

        locationRepository.deleteById(id);
        return true;
    }

    public LocationDTO toDTO(LocationEntity entity) {
        LocationDTO dto = new LocationDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setProvence(entity.getProvence());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setDistrict(entity.getDistrict());
        return dto;
    }

    public LocationDTO get(String s) {
        LocationEntity entity = locationRepository.findById(s).orElse(null);

        if (entity == null) return null;

        return toDTO(entity);
    }
}
