package com.unicauca.smart_consumption.infrastructure.modules.product.api.sync;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaRepository;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoRepository;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductJpaEntityMapper;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductJsonEntityMapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSyncService {
    private final ProductJpaRepository productJpaRepository;
    private final ProductMongoRepository productMongoRepository;
    private final ProductJsonEntityMapper productJsonEntityMapper;
    private final ProductJpaEntityMapper productJpaEntityMapper;

    public void syncProducts(){
        List<ProductJpaEntity> productsJpa = productJpaRepository.findAll();
        List<Product> productsDomain=productsJpa.stream().map(productJpaEntityMapper::toDomain).toList();
        List<ProductMongoEntity> productMongoLst = productsDomain.stream().map(productJsonEntityMapper::toTarget).toList();
        productMongoRepository.saveAll(productMongoLst);
    }
}
