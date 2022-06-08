package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.GuideDTO;
import com.company.dto.PriceDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.dto.request.TripFilterDTO;
import com.company.dto.request.TripFirstDTO;
import com.company.dto.request.TripUploadPhotoDTO;
import com.company.dto.response.ActionDTO;
import com.company.dto.response.TripResponseDTO;
import com.company.dto.trip.TripDTO;
import com.company.entity.GuideEntity;
import com.company.entity.ProfileEntity;
import com.company.entity.TripEntity;
import com.company.enums.ProfileRole;
import com.company.exception.AppForbiddenException;
import com.company.mapper.TripFilterInfoMapper;
import com.company.mapper.TripRateMapper;
import com.company.repository.GuideRepository;
import com.company.repository.TripRepository;
import com.company.repository.custom.TripFilterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final LocationService locationService;

    private final GuideRepository guideRepository;
    private final TripFilterRepository tripFilterRepository;

    private final FacilityService facilityService;

    private final PriceService priceService;

    private final AttachService attachService;


    public ActionDTO create(TripFirstDTO dto) {
        List<String> locationList =
                dto.getTravelLocations()
                        .stream()
                        .map(locationDTO -> locationService.create(locationDTO).getId())
                        .toList();


        GuideEntity guideEntity = guideRepository.
                findByProfile_PhoneNumber(EntityDetails.getProfile().getPhoneNumber()).orElse(null);

        if (guideEntity == null ||
                !guideEntity.getProfile().getRole().equals(ProfileRole.ROLE_GUIDE))
            throw new AppForbiddenException("Not access!");

        TripEntity entity = new TripEntity();

        entity.setDescription(dto.getDesc());
        entity.setName(dto.getName());
        entity.setMaxPeople(dto.getMaxPeople());
        entity.setMinPeople(dto.getMinPeople());
        entity.setGuideId(guideEntity.getId());
        entity.setPlaces(locationList);


        tripRepository.save(entity);

        return new ActionDTO(true);
    }

    public ActionDTO updateDetail(String tripId, String priceId, List<String> facility) {

        TripEntity tripEntity = get(tripId);

        if (tripEntity == null) return new ActionDTO("Trip","trip not found!",false);


        tripRepository.updateDetail(priceId, tripId, facility);

        return new ActionDTO(true);
    }

    public PageImpl<TripDTO> getTrip(int page, int size) {
        ProfileEntity entity = EntityDetails.getProfile();

        Pageable pageable =
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));


        Page<TripEntity> entityPage = tripRepository.findByGuide_Profile_PhoneNumber(entity.getPhoneNumber(), pageable);

        List<TripDTO> tripDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(tripDTOS, pageable, entityPage.getTotalElements());
    }

    public List<TripDTO> filter(TripFilterDTO dto) {
        return tripFilterRepository
                .filter(dto)
                .stream()
                .map(this::toDTOMapper)
                .toList();
    }


    public List<TripResponseDTO> top10(int page, int size) {

        Pageable of = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rate"));

        Page<TripRateMapper> mapperPage = tripRepository.getRate(of);


        List<TripResponseDTO> tripList = new LinkedList<>();


        mapperPage.forEach(mapper -> {
            TripResponseDTO dto = toDTOMapper(mapper);
            dto.setFacility(mapper.getFacilities().
                    stream().map(facilityService::get).toList());
            tripList.add(dto);
        });

        return tripList;
    }


    public TripResponseDTO toDTOMapper(TripRateMapper mapper) {
        TripResponseDTO dto = new TripResponseDTO();
        dto.setName(mapper.getTitle());
        dto.setCreatedDate(mapper.getCreated_date());
        dto.setMinPeople(mapper.getMin_people());
        dto.setMaxPeople(mapper.getMax_people());
        dto.setPhoneNumber(mapper.getPhone());
        dto.setPrice(new PriceDTO(mapper.getPrice_id(), mapper.getPrice_cost(), mapper.getPrice_currency(), mapper.getPrice_type()));
        dto.setRate(mapper.rate());
        dto.setId(mapper.getId());

        return dto;
    }

    public TripDTO toDTOMapper(TripFilterInfoMapper mapper) {
        TripDTO dto = new TripDTO();
        dto.setId(mapper.getT_id());
        dto.setName(mapper.getT_name());
        dto.setDescription(mapper.getT_desc());
        dto.setPhoneNumber(mapper.getT_phone());
        dto.setMaxPeople(mapper.getT_max());
        dto.setMinPeople(mapper.getT_min());
        dto.setRate(mapper.getT_rate());

        dto.setPrice(new PriceDTO(mapper.getP_id(), mapper.getP_cost(), mapper.getP_currency(), mapper.getP_type()));

        dto.setGuide(new GuideDTO(mapper.getG_id(), mapper.getG_phone(), mapper.getG_bio(), mapper.getG_ishiring(), mapper.getG_rate(),
                new ProfileDTO(mapper.getGp_id(), mapper.getGp_name(), mapper.getGp_surname(), mapper.getGp_photo())));

        // TODO: 03-Jun-22 Trip Filter check it

        return dto;
    }

    public TripDTO toDTO(TripEntity entity) {
        TripDTO dto = new TripDTO();
        dto.setDescription(entity.getDescription());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPriceId(entity.getPriceId());
        dto.setGuideId(entity.getGuideId());
        dto.setMaxPeople(entity.getMaxPeople());
        dto.setMinPeople(entity.getMinPeople());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }


    public TripEntity get(String tripId) {
        Optional<TripEntity> optional = tripRepository.findById(tripId);

        if (optional.isEmpty()) {
            return null;
        }
        return optional.get();
    }

}
