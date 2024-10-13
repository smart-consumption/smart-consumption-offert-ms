package com.unicauca.smart_consumption_offert_ms.domain.product.ports.out;

import com.unicauca.smart_consumption_offert_ms.domain.product.Product;

import java.util.List;

/**
 * Interface that defines CRUD operations for the {@link Product} entity.
 */
public interface IProductCommandRepository {

  /**
   * Creates a new product in the system.
   *
   * @param product The {@link Product} to be created.
   * @return The created {@link Product}.
   */
  Product createProduct(Product product);

  Product getProduct(String productId);
  List<Product> getAllProducts();

}
