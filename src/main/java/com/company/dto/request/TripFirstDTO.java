package com.company.dto.request;

import com.company.dto.BaseDTO;
import com.company.dto.LocationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class TripFirstDTO extends BaseDTO {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Desc cannot be null")
    private String desc;

    @Positive(message = "MaxPeople can be positive number")
    private Integer maxPeople;

    @Positive(message = "MinPeople can be positive number")
    private Integer minPeople;

    private List<LocationDTO> travelLocations;

}
