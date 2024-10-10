package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductJsonEntityMapper extends EntityMapper<ProductMongoEntity, Product> {
}
