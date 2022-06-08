package com.company.dto;


import com.company.enums.Currency;
import com.company.enums.TourType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PriceDTO extends BaseDTO {

    @Positive(message = "cost must be positive")
    private Long cost;

    @NotNull(message = "Currency required")
    private Currency currency;

    @NotNull(message = "Type required")
    private TourType type;

    public PriceDTO(String id, Long cost, Currency currency, TourType type) {
        super.id = id;
        this.cost = cost;
        this.currency = currency;
        this.type = type;
    }
}
