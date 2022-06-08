package com.company.dto.request;

import com.company.dto.PriceDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TripSecondDTO {


    private ArrayList<String> photos;

    private PriceDTO price;

    private ArrayList<FacilityDTO> facilities;

}
