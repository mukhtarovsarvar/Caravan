package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "trip_location")
@Getter
@Setter
public class TripLocationEntity extends BaseEntity {



    @Column(nullable = false)
    private String photo;

    @Column(name = "location_id")
    private String locationId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private LocationEntity location;

}
