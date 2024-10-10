package com.unicauca.smart_consumption.domain.product.ports.in;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.product.Product;

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
  ResponseDto<Product> createProduct(Product product);

  /**
   * Updates an existing product in the system.
   *
   * @param id The ID of the {@link Product} to be updated.
   * @param product The {@link Product} with updated information.
   * @return A {@link ResponseDto} containing the updated product and an HTTP status code.
   */
  ResponseDto<Product> updateProduct(String id, Product product);

  /**
   * Deletes an existing product from the system.
   *
   * @param id The ID of the {@link Product} to be deleted.
   * @return A {@link ResponseDto} indicating the result of the operation with an HTTP status code.
   */
  ResponseDto<Void> deleteProduct(String id);


}
