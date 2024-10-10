package com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Embeddable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SustainabilityCriteriaEmbeddable {
    private double carbonFootprint;
    private double energyEfficiency;
    private double resourceUsage;
    private double wasteManagement;
    private double sustainabilityScore;
}
