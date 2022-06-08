package com.company.dto.response;

import com.company.dto.GuideDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainResponseDTO extends ActionDTO {

    private List<GuideDTO> topGuides;

    private List<TripResponseDTO> topTrips;

}
