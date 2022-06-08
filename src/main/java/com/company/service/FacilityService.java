package com.company.service;

import com.company.dto.response.ActionDTO;
import com.company.dto.request.FacilityDTO;
import com.company.entity.FacilityEntity;
import com.company.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;


    public ActionDTO create(FacilityDTO dto){

        FacilityEntity entity = new FacilityEntity();
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());

        facilityRepository.save(entity);

        return new ActionDTO(true);
    }

    public FacilityDTO get(String id){
        FacilityEntity entity = facilityRepository.findById(id).orElse(null);

        if(entity == null) return null;

        return toDTO(entity);
    }

    private FacilityDTO toDTO(FacilityEntity entity) {

        FacilityDTO dto = new FacilityDTO();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());

        return dto;
    }
}
