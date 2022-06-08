package com.company.entity;


import com.company.enums.Currency;
import com.company.enums.TourType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "price")
@Getter
@Setter
public class PriceEntity extends BaseEntity {

    @Column(name = "cost")
    private Long cost;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    @Enumerated(EnumType.STRING)
    private TourType type;

}