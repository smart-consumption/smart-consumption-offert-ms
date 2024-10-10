package com.unicauca.smart_consumption.domain.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class SustainabilityCriteria {
    private double carbonFootprint;
    private double energyEfficiency;
    private final double resourceUsage;
    private final double wasteManagement;
    private double sustainabilityScore;

    public SustainabilityCriteria(double carbonFootprint, double energyEfficiency,
                                  double resourceUsage, double wasteManagement) {
        this.carbonFootprint = carbonFootprint;
        this.energyEfficiency = energyEfficiency;
        this.resourceUsage = resourceUsage;
        this.wasteManagement = wasteManagement;
        this.sustainabilityScore = calculateSustainabilityScore();
    }

    private double calculateSustainabilityScore() {
        return (energyEfficiency * 0.4) + (resourceUsage * 0.3) + (wasteManagement * 0.2) - (carbonFootprint * 0.1);
    }

    public void setCarbonFootprint(double carbonFootprint) {
        this.carbonFootprint = carbonFootprint;
        this.sustainabilityScore = calculateSustainabilityScore();
    }

    public void setEnergyEfficiency(double energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
        this.sustainabilityScore = calculateSustainabilityScore();
    }

}
