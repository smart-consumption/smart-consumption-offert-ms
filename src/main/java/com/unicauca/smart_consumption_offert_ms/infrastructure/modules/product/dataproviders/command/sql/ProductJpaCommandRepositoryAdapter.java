package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.command.sql;


import org.springframework.stereotype.Service;

import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.out.IProductCommandRepository;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.mapper.ProductJpaEntityMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductJpaCommandRepositoryAdapter implements IProductCommandRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductJpaEntityMapper productPostgresMapper;

    @Override
    public Product createProduct(Product product) {
        ProductJpaEntity entity = productPostgresMapper.toTarget(product);
        return productPostgresMapper.toDomain(productJpaRepository.save(entity));
    }

    @Override
    public Product getProduct(String productId) {
        ProductJpaEntity entity = productJpaRepository.findById(productId).orElse(null);
        return productPostgresMapper.toDomain(entity);
    }

    @Override
    public List<Product> getAllProducts() {
        return productJpaRepository.findAll().stream()
                .map(productPostgresMapper::toDomain).toList();
    }

}
