package com.company.mapper;


import com.company.enums.Currency;
import com.company.enums.TourType;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRateMapper {

    String getId();

    String getTitle();

    String getDescription();

    String getPrice_id();

    Long getPrice_cost();

    Currency getPrice_currency();

    TourType getPrice_type();

    Integer getMin_people();

    Integer getMax_people();

    String getGuide_id();

    String getPhone();

    List<String> getFacilities();

    List<String> getPlaces();

    LocalDateTime getCreated_date();

    Double rate();
}
