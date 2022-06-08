package com.company.entity;

import com.company.enums.Region;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "district")
@NoArgsConstructor
public class DistrictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Region region;

    public DistrictEntity(String name, Region region) {
        this.name = name;
        this.region = region;
    }
}
