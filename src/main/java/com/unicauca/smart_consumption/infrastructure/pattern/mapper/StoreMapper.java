package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.store.Store;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.StoreDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper extends EntityMapper<StoreDto, Store>{
}
