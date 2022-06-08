package com.company.dto;

import com.company.dto.profile.ProfileDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class GuideDTO extends BaseDTO {

    private String profileId;

    private String secondPhoneNumber;

    private String biography;

    private Boolean isHiring;

    private double rate;

    private PriceDTO price;

    private List<LanguageDTO> languages;

    private List<LocationDTO> travelLocations;

    private String attachId;

    private ProfileDTO profile;

    public GuideDTO(String id, String secondPhoneNumber, String biography, Boolean isHiring, double rate, ProfileDTO profileDTO) {
        super.id = id;
        this.secondPhoneNumber = secondPhoneNumber;
        this.biography = biography;
        this.isHiring = isHiring;
        this.rate = rate;
        this.profile = profileDTO;
    }
}
