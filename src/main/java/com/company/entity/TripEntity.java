package com.company.entity;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trip")
@Getter
@Setter
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class TripEntity extends BaseEntity {


    private String name;

    private String description;

    private Integer minPeople;

    private Integer maxPeople;

    @Column(name = "price_id")
    private String priceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", insertable = false, updatable = false)
    private PriceEntity price;

    @Column(name = "guide_id")
    private String guideId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id", insertable = false, updatable = false)
    private GuideEntity guide;

    private String phoneNumber;

    private Double rate;

    @Type(type = "list-array")
    @Column(name = "places_list", columnDefinition = "varchar[]")
    private List<String> places;


    @Type(type = "list-array")
    @Column(name = "facility_list", columnDefinition = "varchar[]")
    private List<String> facility;


    //  reviews:ArrayList<Comment>?=null
    //  attendancesProfileId:ArrayList<String>,
    //  val photos:ArrayList<TourPhoto>,


}
