package com.unicauca.smart_consumption.infrastructure.pattern.dto;

import com.unicauca.smart_consumption.domain.offer.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDto {
    private String id;
    private String description;
    private Period period;
    private ProductDto product;
    private StoreDto store;
    private double discountPercentage;
    private double discountedPrice;
}
