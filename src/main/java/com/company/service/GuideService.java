package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.*;
import com.company.dto.request.TripFilterDTO;
import com.company.dto.response.ActionDTO;
import com.company.dto.response.TripResponseDTO;
import com.company.dto.trip.TripDTO;
import com.company.entity.GuideEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.exception.ItemNotFoundException;
import com.company.mapper.GuideFilterMapper;
import com.company.mapper.GuideRateMapper;
import com.company.mapper.TripRateMapper;
import com.company.repository.GuideRepository;
import com.company.repository.LanguageRepository;
import com.company.repository.LocationRepository;
import com.company.repository.ProfileRepository;
import com.company.repository.custom.GuideFilterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuideService {

    private final GuideRepository guideRepository;

    private final GuideFilterRepository guideFilterRepository;
    private final LocationService locationService;
    private final LanguageService languageService;
    private final PriceService priceService;

    private final ProfileRepository profileRepository;
    private final LanguageRepository languageRepository;

    private final LocationRepository locationRepository;

    private final ProfileService profileService;

    public ActionDTO create(GuideDTO dto) {

        ProfileEntity profileEntity = EntityDetails.getProfile();

        GuideEntity guideEntity = guideRepository.findByProfile_PhoneNumber(profileEntity.getPhoneNumber()).orElse(null);

        if(guideEntity != null) return new  ActionDTO("Profile","your are guide!",false);

        PriceDTO price = priceService.create(dto.getPrice());

        List<String> languages = new LinkedList<>();

        List<String> locations = new LinkedList<>();

        for (LanguageDTO language : dto.getLanguages()) {
            languages.add(languageService.create(language).getId());
        }

        for (LocationDTO location : dto.getTravelLocations()) {
            locations.add(locationService.create(location).getId());
        }

        GuideEntity entity = new GuideEntity();
        entity.setBiography(dto.getBiography());
        entity.setLanguageList(languages);
        entity.setLocationList(locations);
        entity.setPhoneNumber(dto.getSecondPhoneNumber());
        entity.setPriceId(price.getId());
        entity.setRate(0d);
        entity.setProfileId(profileEntity.getId());

        profileRepository.updateRole(ProfileRole.ROLE_GUIDE,profileEntity.getId());

        guideRepository.save(entity);

        return new ActionDTO(true);
    }


    public ActionDTO update(GuideDTO dto) {

        ProfileEntity profileEntity = EntityDetails.getProfile();

        Optional<GuideEntity> guide = guideRepository.findById(profileEntity.getId());

        if (guide.isEmpty() || !guide.get().getProfile().getRole().equals(ProfileRole.ROLE_GUIDE))
            return new ActionDTO("Guide", " guide not found!", false);


        GuideEntity guideEntity = guide.get();

        PriceDTO price = priceService.create(dto.getPrice());

        List<String> languages = new LinkedList<>();

        List<String> locations = new LinkedList<>();

        List<String> languageList = guideEntity.getLanguageList();
        List<String> locationList = guideEntity.getLocationList();

        Thread thread = new Thread(() -> {
            locationRepository.deleteAllById(locationList);
            languageRepository.deleteAllById(languageList);
        });

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (th, ex) -> {

        };
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);

        thread.start();


        for (LanguageDTO language : dto.getLanguages()) {
            languages.add(languageService.create(language).getId());
        }

        for (LocationDTO location : dto.getTravelLocations()) {
            locations.add(locationService.create(location).getId());
        }

        guideEntity.setBiography(dto.getBiography());
        guideEntity.setLanguageList(languages);
        guideEntity.setLocationList(locations);
        guideEntity.setPhoneNumber(dto.getSecondPhoneNumber());
        guideEntity.setPriceId(price.getId());
        guideEntity.setProfileId(dto.getProfileId());

        guideRepository.save(guideEntity);

        return new ActionDTO(true);
    }


    public Boolean getGuideHiring() {

        ProfileEntity profile = EntityDetails.getProfile();

        GuideEntity guideEntity = guideRepository.findByProfileId(profile.getId()).orElse(null);

        if (Optional.ofNullable(guideEntity).isPresent()) {
            return guideEntity.getIsHiring();
        }

        return false;
    }


    public List<GuideDTO> getTop10(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rate"));

        List<GuideRateMapper> rate = guideRepository.getRate(pageable, ProfileRole.ROLE_GUIDE);

        return rate.stream().map(this::toDTO).toList();
    }


    public GuideDTO toDTO(GuideRateMapper mapper) {
        GuideDTO dto = new GuideDTO();

        dto.setPrice(new PriceDTO(mapper.getPriceId(), mapper.getPrice_cost(), mapper.getPrice_currency(), mapper.getPrice_type()));

        dto.setRate(mapper.getRate());

        dto.setCreatedDate(mapper.getCreateDate());

        dto.setBiography(mapper.getBio());

        dto.setId(mapper.getGuideId());

        dto.setProfileId(mapper.getProfileId());

        dto.setIsHiring(mapper.getHiring());

        dto.setSecondPhoneNumber(mapper.getPhone());


        if (mapper.getLanguage().size() > 0) {
            dto.setLanguages(languageRepository.findAllById(mapper.getLanguage()).
                    stream().map(languageService::toDTO).toList());
        }

        if (mapper.getLocations().size() > 0) {

            dto.setTravelLocations(locationRepository.findAllById(mapper.getLocations())
                    .stream().map(locationService::toDTO).toList());

        }

        return dto;
    }
    public GuideDTO toDTO(GuideFilterMapper mapper) {
        GuideDTO dto = new GuideDTO();

        dto.setPrice(new PriceDTO(mapper.getPriceId(), mapper.getPrice_cost(), mapper.getPrice_currency(), mapper.getPrice_type()));

        dto.setRate(mapper.getRate());

        dto.setCreatedDate(mapper.getCreateDate());

        dto.setBiography(mapper.getBio());

        dto.setId(mapper.getGuideId());

        dto.setProfileId(mapper.getProfileId());

        dto.setIsHiring(mapper.getHiring());

        dto.setSecondPhoneNumber(mapper.getPhone());


        if (mapper.getLanguage().size() > 0) {
            dto.setLanguages(languageRepository.findAllById(mapper.getLanguage()).
                    stream().map(languageService::toDTO).toList());
        }

        if (mapper.getLocations().size() > 0) {

            dto.setTravelLocations(locationRepository.findAllById(mapper.getLocations())
                    .stream().map(locationService::toDTO).toList());

        }

        return dto;
    }
    public GuideDTO toDTO(GuideEntity entity) {

        GuideDTO dto = new GuideDTO();

        dto.setPrice(priceService.get(entity.getPriceId()));

        dto.setRate(entity.getRate());

        dto.setCreatedDate(entity.getCreatedDate());

        dto.setBiography(entity.getBiography());

        dto.setId(entity.getId());

        dto.setProfileId(entity.getProfileId());

        dto.setIsHiring(entity.getIsHiring());

        dto.setSecondPhoneNumber(entity.getPhoneNumber());


        if (entity.getLanguageList().size() > 0) {
            dto.setLanguages(languageRepository.findAllById(entity.getLanguageList()).
                    stream().map(languageService::toDTO).toList());
        }

        if (entity.getLocationList().size() > 0) {

            dto.setTravelLocations(locationRepository.findAllById(entity.getLocationList())
                    .stream().map(locationService::toDTO).toList());

        }

        return dto;
    }

    public GuideEntity get(String id) {
        return guideRepository
                .findById(id)
                .orElse(null);
    }

    public  GuideDTO getGuide(String guideId){
        GuideEntity guideEntity = get(guideId);
        if (Optional.ofNullable(guideEntity).isEmpty())
            throw new ItemNotFoundException("Guide profile not found!");


        return toDTO(guideEntity);
    }


    public Boolean updateIsHiring() {

        ProfileEntity profile = EntityDetails.getProfile();

        GuideEntity guideEntity = guideRepository.findByProfileId(profile.getId()).orElse(null);



        if (guideEntity.getIsHiring()) {
            guideRepository.updateIsHiring(profile.getPhoneNumber(), false);
            return false;
        } else {
            guideRepository.updateIsHiring(profile.getPhoneNumber(), true);
            return true;
        }
    }


    public ActionDTO delete() {
        ProfileEntity profile = EntityDetails.getProfile();

        GuideEntity guideEntity = guideRepository.findByProfileId(profile.getId()).orElseThrow(()->{
            log.warn("Guide not found!");
            throw new ItemNotFoundException("Guide is not found");
        });



        profileService.changeRole(ProfileRole.ROLE_TOURIST, profile.getId());

        guideRepository.delete(guideEntity);

        return new ActionDTO(true);
    }



    public List<GuideDTO> filter(GuideFilterDTO dto) {
        return guideFilterRepository
                .filter(dto)
                .stream()
                .map(this::toDTO)
                .toList();
    }




}
