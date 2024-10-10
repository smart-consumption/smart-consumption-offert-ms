package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.infrastructure.modules.city.dataproviders.jpa.CityJPAEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class CityJPAMapperImpl implements CityJPAMapper {

    @Override
    public City toDomain(CityJPAEntity dto) {
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
    public CityJPAEntity toTarget(City entity) {
        if ( entity == null ) {
            return null;
        }

        CityJPAEntity cityJPAEntity = new CityJPAEntity();

        cityJPAEntity.setDepartment( entity.getDepartment() );
        cityJPAEntity.setId( entity.getId() );
        cityJPAEntity.setName( entity.getName() );

        return cityJPAEntity;
    }
}
