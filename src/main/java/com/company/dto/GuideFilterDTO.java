package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuideFilterDTO {

    private PriceDTO minPrice;

    private PriceDTO maxPrice;

    private Integer minRating;

    private Integer maxRating;

    private String gender;
}
