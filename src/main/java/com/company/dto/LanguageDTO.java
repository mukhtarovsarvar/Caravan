package com.company.dto;

import com.company.enums.LanguageLevel;
import com.company.enums.LanguageName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LanguageDTO   {
    private  String id;
    private  LocalDateTime createdDate;
    private  LocalDateTime updatedDate;
    private LanguageName name;
    private LanguageLevel level;
}
