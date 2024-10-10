package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.offer.Offer;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.OfferDto;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link OfferDto} and {@link Offer}.
 * <p>The implementation of this interface is automatically generated by MapStruct during the build process, ensuring
 * that the mappings are efficient and error-free.</p>
 *
 * @see OfferDto
 * @see Offer
 * @see EntityMapper
 */
@Mapper(componentModel = "spring")
public interface OfferMapper extends EntityMapper<OfferDto, Offer>{
}
