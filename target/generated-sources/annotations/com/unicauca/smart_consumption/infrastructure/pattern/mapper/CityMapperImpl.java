package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CityDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public City toDomain(CityDto dto) {
        if ( dto == null ) {
            return null;
        }

        City city = new City();

        city.setDepartment( dto.getDepartment() );
        city.setId( dto.getId() );
        city.setName( dto.getName() );

        return city;
    }

    @Override
    public CityDto toTarget(City entity) {
        if ( entity == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setDepartment( entity.getDepartment() );
        cityDto.setId( entity.getId() );
        cityDto.setName( entity.getName() );

        return cityDto;
    }
}
