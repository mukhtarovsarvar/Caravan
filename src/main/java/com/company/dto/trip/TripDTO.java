package com.company.dto.trip;

import com.company.dto.BaseDTO;
import com.company.dto.GuideDTO;
import com.company.dto.PriceDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripDTO extends BaseDTO {

    private String name;

    private String description;

    private String phoneNumber;

    private Double rate;

    private Integer maxPeople;

    private Integer minPeople;

    private String priceId;
    private PriceDTO price;

    private String guideId;
    private GuideDTO guide;

}
