package com.unicauca.smart_consumption.domain.product.ports.out;

import com.unicauca.smart_consumption.domain.product.Product;

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

  /**
   * Updates an existing product in the system.
   *
   * @param id The ID of the {@link Product} to be updated.
   * @param product The {@link Product} with updated information.
   * @return The updated {@link Product}.
   */
  Product updateProduct(String id, Product product);

  /**
   * Deletes an existing product from the system.
   *
   * @param id The ID of the {@link Product} to be deleted.
   */
  void deleteProduct(String id);

}
