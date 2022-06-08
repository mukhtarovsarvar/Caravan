package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO extends BaseDTO {

    private String provence;

    private String district;

    private String description;
    
}
