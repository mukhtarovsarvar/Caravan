package com.company.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripFilterDTO {

    private Long minCost;
    private Long maxCost;

    private Integer minPeople;
    private Integer maxPeople;

    private Integer minRate;
    private Integer maxRate;

}
