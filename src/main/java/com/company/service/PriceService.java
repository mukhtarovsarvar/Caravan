package com.company.service;


import com.company.dto.PriceDTO;
import com.company.entity.PriceEntity;
import com.company.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceDTO create(PriceDTO dto) {
        PriceEntity entity = new PriceEntity();
        entity.setCost(dto.getCost());
        entity.setCurrency(dto.getCurrency());
        entity.setType(dto.getType());
        priceRepository.save(entity);
        return toDTO(entity);
    }

    public PriceDTO get(String id){
        PriceEntity priceEntity = priceRepository.findById(id).orElse(null);

        if(priceEntity == null){
            return null;
        }
        return toDTO(priceEntity);
    }
    public PriceDTO toDTO(PriceEntity entity) {
        PriceDTO dto = new PriceDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }
}
