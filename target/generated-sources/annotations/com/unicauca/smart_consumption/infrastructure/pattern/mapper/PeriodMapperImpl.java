package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.offer.Period;
import com.unicauca.smart_consumption.infrastructure.modules.offer.dataproviders.jpa.PeriodEmbeddable;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class PeriodMapperImpl implements PeriodMapper {

    @Override
    public Period toDomain(PeriodEmbeddable dto) {
        if ( dto == null ) {
            return null;
        }

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        startDate = dto.getStartDate();
        endDate = dto.getEndDate();

        Period period = new Period( startDate, endDate );

        return period;
    }

    @Override
    public PeriodEmbeddable toTarget(Period entity) {
        if ( entity == null ) {
            return null;
        }

        PeriodEmbeddable periodEmbeddable = new PeriodEmbeddable();

        periodEmbeddable.setEndDate( entity.getEndDate() );
        periodEmbeddable.setStartDate( entity.getStartDate() );

        return periodEmbeddable;
    }
}
