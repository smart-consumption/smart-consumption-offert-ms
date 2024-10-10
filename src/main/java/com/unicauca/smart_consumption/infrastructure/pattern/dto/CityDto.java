package com.unicauca.smart_consumption.infrastructure.pattern.dto;

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
public class CityDto {
    private String id;
    private String name;
    private String department;
}
