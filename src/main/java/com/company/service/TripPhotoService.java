package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.PriceDTO;
import com.company.dto.request.TripSecondDTO;
import com.company.dto.response.ActionDTO;
import com.company.entity.FacilityEntity;
import com.company.entity.ProfileEntity;
import com.company.entity.TripEntity;
import com.company.entity.TripPhotoEntity;
import com.company.exception.AppForbiddenException;
import com.company.repository.FacilityRepository;
import com.company.repository.TripPhotosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPhotoService {

    private final TripPhotosRepository tripPhotosRepository;

    private final FacilityRepository facilityRepository;

    private final TripService tripService;

    private final PriceService priceService;

    public ActionDTO create(String tripId, TripSecondDTO dto) {

        TripEntity tripEntity = tripService.get(tripId);

        ProfileEntity profile = EntityDetails.getProfile();

        if (tripEntity == null)
            return new ActionDTO("trip", "trip not found", false);

        if(!tripEntity.getGuide().getId().equals(profile.getId()))
            throw new AppForbiddenException("Not access!");

        dto.getPhotos().forEach(photo -> {

            TripPhotoEntity entity = new TripPhotoEntity();
            entity.setTripId(tripId);
            entity.setAttachId(photo);
            tripPhotosRepository.save(entity);

        });



        List<String> facility = new ArrayList<>();

        dto.getFacilities().forEach(s -> {

            FacilityEntity entity = new FacilityEntity();
            entity.setTitle(s.getTitle());
            entity.setDescription(s.getDescription());
           facility.add(facilityRepository.save(entity).getId());

        });

        PriceDTO priceDTO = priceService.create(dto.getPrice());


        ActionDTO actionDTO = tripService.updateDetail(tripId, priceDTO.getId(), facility);

        if(!actionDTO.getStatus()) return actionDTO;

        return new ActionDTO(true);
    }




}
