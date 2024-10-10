package com.unicauca.smart_consumption.domain.product;

import lombok.Getter;

@Getter
public enum Rating {
    TERRIBLE(1),
    POOR(2),
    AVERAGE(3),
    GOOD(4),
    EXCELLENT(5);

    private final int value;

    Rating(int value) {
        this.value = value;
    }

}

