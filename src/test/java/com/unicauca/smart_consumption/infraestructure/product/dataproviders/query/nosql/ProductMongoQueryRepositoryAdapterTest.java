package com.unicauca.smart_consumption.infraestructure.product.dataproviders.query.nosql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductJsonEntityMapper;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoQueryRepositoryAdapter;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ProductMongoQueryRepositoryAdapterTest {

  @Mock
  private ProductMongoRepository productMongoRepository;

  private final ProductJsonEntityMapper productMongoMapper = Mappers.getMapper(ProductJsonEntityMapper.class);

  @InjectMocks
  private ProductMongoQueryRepositoryAdapter productMongoQueryRepositoryAdapter;


  @Test
  void testFindProductById_Success() {
    ReflectionTestUtils.setField(this.productMongoQueryRepositoryAdapter, "productMongoMapper", this.productMongoMapper);
    String productId = "123";
    ProductMongoEntity mongoEntity = new ProductMongoEntity();
    when(productMongoRepository.findById(productId)).thenReturn(Optional.of(mongoEntity));
    Optional<Product> result = productMongoQueryRepositoryAdapter.findProductById(productId);
    assertTrue(result.isPresent(), "Expected product to be present");
    verify(productMongoRepository).findById(productId);
  }

  @Test
  void testFindProductById_NotFound() {
    ReflectionTestUtils.setField(this.productMongoQueryRepositoryAdapter, "productMongoMapper", this.productMongoMapper);
    String productId = "456";
    when(productMongoRepository.findById(productId)).thenReturn(Optional.empty());
    Optional<Product> result = productMongoQueryRepositoryAdapter.findProductById(productId);
    assertFalse(result.isPresent(), "Expected product to be absent");
    verify(productMongoRepository).findById(productId);
  }

  @Test
  void testFindAllProducts_Success() {
    ReflectionTestUtils.setField(this.productMongoQueryRepositoryAdapter, "productMongoMapper", this.productMongoMapper);
    ProductMongoEntity mongoEntity1 = new ProductMongoEntity();
    ProductMongoEntity mongoEntity2 = new ProductMongoEntity();

    when(productMongoRepository.findAll()).thenReturn(List.of(mongoEntity1, mongoEntity2));
    List<Product> result = productMongoQueryRepositoryAdapter.findAllProducts();
    assertEquals(2, result.size(), "Expected two products");
    verify(productMongoRepository).findAll();
  }

  @Test
  void testFindAllProducts_Empty() {
    ReflectionTestUtils.setField(this.productMongoQueryRepositoryAdapter, "productMongoMapper", this.productMongoMapper);
    when(productMongoRepository.findAll()).thenReturn(Collections.emptyList());
    List<Product> result = productMongoQueryRepositoryAdapter.findAllProducts();
    assertTrue(result.isEmpty(), "Expected product list to be empty");
    verify(productMongoRepository).findAll();
  }
}