package com.company.dto.response;

import com.company.dto.*;
import com.company.dto.request.CommentDTO;
import com.company.dto.request.FacilityDTO;
import com.company.dto.trip.TripPhotoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripResponseDTO extends BaseDTO {


    private String name;

    private List<TripPhotoDTO> photos;

    private List<FacilityDTO> facility;

    private List<LocationDTO> places;

    private String description;

    private PriceDTO price;

    private Integer minPeople;

    private Integer maxPeople;

    private GuideResponse guideProfile;

    private String phoneNumber;

    private Double rate;

    private List<String> attendancesProfileId;

    private List<CommentDTO> reviews;
}
