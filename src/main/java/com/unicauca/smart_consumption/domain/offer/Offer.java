package com.unicauca.smart_consumption.domain.offer;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Offer {
    private String id;
    private String description;
    private Period period;
    private Product product;
    private Store store;
    private double discountPercentage;
    private double discountedPrice;


    public double calculateDiscountedPrice() {
        discountedPrice=product.getPrice() * (1 - discountPercentage / 100);
        return discountedPrice;
    }

    public Offer update(String description, double discountPercentage) {
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        }
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            this.discountPercentage = discountPercentage;
            this.discountedPrice = calculateDiscountedPrice();
        }
        return this;
    }

    public boolean isValid() {
        LocalDateTime today = LocalDateTime.now();
        return period.isWithinPeriod(today);
    }

}


