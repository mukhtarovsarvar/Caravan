package com.company.mapper;

import com.company.entity.GuideEntity;
import com.company.entity.PriceEntity;
import com.company.entity.TripEntity;
import com.company.enums.Currency;
import com.company.enums.TourType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class TripFilterInfoMapper {

    private String t_id;
    private String t_name;
    private String t_desc;
    private Integer t_max;
    private Integer t_min;
    private String t_phone;
    private Double t_rate;

    private String p_id;
    private Long p_cost;
    private Currency p_currency;
    private TourType p_type;

    private String g_id;
    private String g_phone;
    private String g_bio;
    private Boolean g_ishiring;
    private Double g_rate;

    private String gp_id;
    private String gp_name;
    private String gp_surname;
    private String gp_photo;


}
