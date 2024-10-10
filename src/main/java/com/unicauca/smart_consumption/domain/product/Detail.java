package com.unicauca.smart_consumption.domain.product;

import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Detail {

    private final String description;
    private final String specifications;

    public Detail(String description, String specifications) {
        if (!Objects.nonNull(description) || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description cannot be null or empty.");
        }
        this.description = description;
        this.specifications = specifications;
    }
}
