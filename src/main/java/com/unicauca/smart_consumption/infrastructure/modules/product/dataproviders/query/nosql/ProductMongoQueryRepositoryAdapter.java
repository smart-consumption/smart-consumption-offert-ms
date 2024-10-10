package com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.out.IProductQueryRepository;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductJsonEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductMongoQueryRepositoryAdapter implements IProductQueryRepository {

  private final ProductMongoRepository productMongoRepository;
  private final ProductJsonEntityMapper productMongoMapper;

  @Override
  public Optional<Product> findProductById(String id) {
    return productMongoRepository.findById(id)
        .map(productMongoMapper::toDomain);
  }

  @Override
  public List<Product> findAllProducts() {
    return productMongoRepository.findAll().stream()
        .map(productMongoMapper::toDomain)
        .toList();
  }

  @Override
  public List<Product> findAllByIdIn(List<String> ids) {
    return this.productMongoRepository.findAllById(ids).
            stream().map(productMongoMapper::toDomain).toList();
  }

}