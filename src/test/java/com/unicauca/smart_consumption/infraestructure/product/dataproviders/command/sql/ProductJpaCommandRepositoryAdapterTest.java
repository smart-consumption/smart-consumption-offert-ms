package com.unicauca.smart_consumption.infraestructure.product.dataproviders.command.sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Detail;
import com.unicauca.smart_consumption.domain.product.SustainabilityCriteria;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductJpaEntityMapper;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaCommandRepositoryAdapter;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class ProductJpaCommandRepositoryAdapterTest {

  @Mock
  private ProductJpaRepository productJpaRepository;
  private final ProductJpaEntityMapper productJpaMapper = Mappers.getMapper(ProductJpaEntityMapper.class);
  @InjectMocks
  private ProductJpaCommandRepositoryAdapter productJpaCommandRepositoryAdapter;

  @Test
  void testCreateProduct_Success() {
    ReflectionTestUtils.setField(this.productJpaCommandRepositoryAdapter, "productPostgresMapper", this.productJpaMapper);
    Product product = new Product();
    product.setName("Sample Product");
    ProductJpaEntity entity = ProductJpaEntity.builder().build();
    when(productJpaRepository.save(any(ProductJpaEntity.class))).thenReturn(entity);
    Product result = productJpaCommandRepositoryAdapter.createProduct(product);
    assertNotNull(result, "Expected created product to be not null");
    verify(productJpaRepository).save(any(ProductJpaEntity.class));
  }

  @Test
  void testUpdateProduct_Success() {
    ReflectionTestUtils.setField(this.productJpaCommandRepositoryAdapter, "productPostgresMapper", this.productJpaMapper);
    String productId = "123";
    String testStr = "STR";
    ProductJpaEntity existingEntity = ProductJpaEntity.builder().id(productId).build();
    when(productJpaRepository.findById(productId)).thenReturn(java.util.Optional.of(existingEntity));
    when(productJpaRepository.save(any(ProductJpaEntity.class))).thenReturn(existingEntity);
    Product updatedProduct = new Product();
    updatedProduct.setName("Updated Product");
    updatedProduct.setCategory(new Category(testStr));
    updatedProduct.setDetail(new Detail(testStr,testStr));
    updatedProduct.setSustainabilityCriteria(new SustainabilityCriteria(0,0,0,0));
    Product result = productJpaCommandRepositoryAdapter.updateProduct(productId, updatedProduct);
    assertNotNull(result, "Expected updated product to be not null");
    verify(productJpaRepository).findById(productId);
    verify(productJpaRepository).save(existingEntity);
  }

  @Test
  void testUpdateProduct_NotFound() {
    String productId = "999";
    when(productJpaRepository.findById(productId)).thenReturn(java.util.Optional.empty());
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
        productJpaCommandRepositoryAdapter.updateProduct(productId, new Product())
    );
    assertEquals("Product not found with id " + productId, exception.getMessage());
    verify(productJpaRepository).findById(productId);
    verify(productJpaRepository, never()).save(any());
  }
}
