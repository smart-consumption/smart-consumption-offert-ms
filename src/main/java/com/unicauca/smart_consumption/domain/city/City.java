package com.unicauca.smart_consumption.domain.city;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class City {
    private String id;
    private String name;
    private String department;

    public City update(String name, String department) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (department != null && !department.trim().isEmpty()) {
            this.department = department;
        }
        return this;
    }

}

