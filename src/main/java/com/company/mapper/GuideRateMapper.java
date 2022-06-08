package com.company.mapper;

import com.company.enums.Currency;
import com.company.enums.TourType;

import java.time.LocalDateTime;
import java.util.List;

public interface GuideRateMapper {

     String getGuideId();

     String getProfileId();

     String getPhone();

     String getBio();

     Boolean getHiring();

     String getPriceId();

     Long getPrice_cost();

     Currency getPrice_currency();

     TourType getPrice_type();

     List<String> getLanguage();

     List<String> getLocations();

     Double getRate();

     LocalDateTime getCreateDate();



}
