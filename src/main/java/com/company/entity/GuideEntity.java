package com.company.entity;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "guide")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class GuideEntity extends BaseEntity {


    private String phoneNumber;
    private String biography;
    private Boolean isHiring = false;
    private Double rate;


    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Type(type = "list-array")
    @Column(name = "location_list", columnDefinition = "varchar[]")
    private List<String> locationList;


    @Type(type = "list-array")
    @Column(name = "language_list", columnDefinition = "varchar[]")
    private List<String> languageList;


    @Column(name = "price_id")
    private String priceId;
    @OneToOne
    @JoinColumn(name = "price_id",insertable = false,updatable = false)
    private PriceEntity price;

    @Column(name = "profile_id")
    private String profileId;
    @OneToOne
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

}
