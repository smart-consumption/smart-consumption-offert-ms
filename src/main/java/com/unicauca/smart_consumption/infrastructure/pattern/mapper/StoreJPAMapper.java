package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.store.Store;
import com.unicauca.smart_consumption.infrastructure.modules.store.dataproviders.jpa.StoreJPAEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreJPAMapper extends EntityMapper<StoreJPAEntity, Store>{

}
