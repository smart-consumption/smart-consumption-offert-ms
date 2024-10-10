package com.unicauca.smart_consumption.domain.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public class Category {

    private final String categoryName;

    public Category(String categoryName) {
        if (!Objects.nonNull(categoryName) || categoryName.isEmpty()) {
            throw new IllegalArgumentException("The category categoryName cannot be null or empty");
        }
        this.categoryName = categoryName;
    }

}

