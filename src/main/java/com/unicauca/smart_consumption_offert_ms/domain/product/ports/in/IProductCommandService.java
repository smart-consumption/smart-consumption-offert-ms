package com.unicauca.smart_consumption_offert_ms.domain.product.ports.in;

import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;

import java.util.List;

/**
 * Interface that defines CRUD operations for the {@link Product} entity.
 */
public interface IProductCommandService {

  /**
   * Creates a new product in the system.
   *
   * @param product The {@link Product} to be created.
   * @return A {@link ResponseDto} containing the created product and an HTTP status code.
   */
  void createProduct(Product product);
  ResponseDto<Product> getProduct(String productId);

  ResponseDto<List<Product>> getAllProducts();

}
