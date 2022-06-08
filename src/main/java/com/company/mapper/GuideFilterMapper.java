package com.company.mapper;

import com.company.enums.Currency;
import com.company.enums.TourType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class GuideFilterMapper {

    private String guideId;

    private String profileId;

    private String phone;

    private String bio;

    private Boolean hiring;

    private String priceId;

    private Long price_cost;

    private Currency price_currency;

    private TourType price_type;

    private List<String> language;

    private List<String> locations;

    private Double rate;

    private LocalDateTime createDate;


}
