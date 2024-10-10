package com.unicauca.smart_consumption.domain.store.ports.in;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.store.Store;

import java.util.List;

public interface IStoreService {
    /**
     /**
     * Creates a new store in the system.
     *
     * @param store The {@link Store} to be created.
     * @return A {@link ResponseDto} containing the created {@link Store} and an HTTP status code.
     */
    ResponseDto<Store> createStore(Store store);

    /**
     * Updates an existing store in the system.
     *
     * @param id The ID of the {@link Store} to be updated.
     * @param store The {@link Store} with the updated information.
     * @return A {@link ResponseDto} containing the updated {@link Store} and an HTTP status code.
     */
    ResponseDto<Store> updateStore(String id, Store store);

    /**
     * Deletes an existing store from the system.
     *
     * @param id The ID of the {@link Store} to be deleted.
     * @return A {@link ResponseDto} indicating the result of the operation with an HTTP status code.
     */
    ResponseDto<Void> deleteStore(String id);

    /**
     * Finds a store in the system by its ID.
     *
     * @param id The ID of the {@link Store} to be retrieved.
     * @return A {@link ResponseDto} containing the found {@link Store}, if it exists, and an HTTP status code.
     */
    ResponseDto<Store> findStoreById(String id);

    /**
     * Retrieves a list of all stores in the system.
     *
     * @return A {@link ResponseDto} containing the list of all {@link Store} and an HTTP status code.
     */
    ResponseDto<List<Store>> findAllStores();

    /**
     * Adds a product to the store.
     *
     * @param storeId The ID of the store.
     * @param productId The ID of the product.
     * @return A {@link ResponseDto} containing the list of all {@link Store} and an HTTP status code..
     */
    ResponseDto<Store> addProductsToStore(String storeId, List<String> productId);
}
