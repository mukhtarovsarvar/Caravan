package com.company.service;

import com.company.dto.LocationDTO;
import com.company.dto.request.TripUploadPhotoDTO;
import com.company.entity.TripLocationEntity;
import com.company.repository.TripLocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripLocationService {

    private final TripLocationRepository tripLocationRepository;

    private final TripService tripService;

    private final LocationService locationService;




    public TripUploadPhotoDTO tripUploadPhoto(TripUploadPhotoDTO dto) {

        TripLocationEntity entity = new TripLocationEntity();
        LocationDTO locationDTO = locationService.create(dto.getLocation());
        entity.setLocationId(locationDTO.getId());
        entity.setPhoto(dto.getPhotoId());

        tripLocationRepository.save(entity);

        return new TripUploadPhotoDTO(entity.getId(), entity.getPhoto(), locationDTO);
    }

}
