package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.offer.dataproviders.jpa;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PeriodEmbeddable {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
