package com.company.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "facility")
@Getter
@Setter
public class FacilityEntity extends BaseEntity {

    private String title;

    private String description;
}

