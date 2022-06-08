package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "location")
public class LocationEntity extends BaseEntity {

    private String provence;

    private String district;

    private String description;

}
