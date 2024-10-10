package com.unicauca.smart_consumption.infrastructure.pattern.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String id;
    private String username;
    private String name;
    private List<ReviewDto> reviews;
    private List<ProductDto> watchList;
    private CityDto city;
}
