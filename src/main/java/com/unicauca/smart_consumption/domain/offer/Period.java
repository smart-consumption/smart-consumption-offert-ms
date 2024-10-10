package com.unicauca.smart_consumption.domain.offer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public class Period {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        if (!Objects.nonNull(startDate) || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinPeriod(LocalDateTime date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) && (date.isEqual(endDate) || date.isBefore(endDate));
    }

}
