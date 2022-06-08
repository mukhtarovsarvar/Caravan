package com.company.entity;

import com.company.enums.LanguageLevel;
import com.company.enums.LanguageName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "language")
public class LanguageEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private LanguageName name;


    @Enumerated(EnumType.STRING)
    private LanguageLevel level;
}
