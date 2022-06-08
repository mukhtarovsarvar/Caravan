package com.company.service;

import com.company.dto.LanguageDTO;
import com.company.entity.LanguageEntity;
import com.company.exception.ItemNotFoundException;
import com.company.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageEntity create(LanguageDTO dto){

        LanguageEntity entity= new LanguageEntity();
        entity.setLevel(dto.getLevel());
        entity.setName(dto.getName());

        languageRepository.save(entity);

        return entity;
    }

    public LanguageDTO get(String id){

        LanguageEntity entity = languageRepository.findById(id).orElse(null);

        if(entity == null) return null;

        return toDTO(entity);
    }

    public Boolean delete(String id){
        languageRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("language not found!");
        });

        languageRepository.deleteById(id);
        return true;
    }

    public LanguageDTO toDTO(LanguageEntity entity){
        LanguageDTO dto = new LanguageDTO();
        dto.setLevel(entity.getLevel());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());

        return dto;
    }

}
