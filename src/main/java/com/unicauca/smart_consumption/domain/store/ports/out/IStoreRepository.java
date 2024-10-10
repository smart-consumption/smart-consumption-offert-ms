package com.unicauca.smart_consumption.domain.store.ports.out;

import com.unicauca.smart_consumption.domain.store.Store;

import java.util.List;
import java.util.Optional;

    public interface IStoreRepository {
        /**
         * Creates a new store in the system.
         *
         * @param store The {@link Store} to be created.
         * @return The created {@link Store}.
         */
        Store createStore(Store store);

        /**
         * Updates an existing store in the system.
         *
         * @param id The ID of the {@link Store} to be updated.
         * @param store The {@link Store} with the updated information.
         * @return The updated {@link Store}.
         */
        Store updateStore(String  id, Store store);

        /**
         * Deletes an existing store from the system.
         *
         * @param id The ID of the {@link Store} to be deleted.
         */
        void deleteStore(String  id);

        /**
         * Finds a store in the system by its ID.
         *
         * @param id The ID of the {@link Store} to be retrieved.
         * @return An {@link Optional} containing the found {@link Store} if it exists, or {@link Optional#empty()} if not found.
         */
        Optional<Store> findStoreById(String  id);

        /**
         * Gets a list of all stores in the system.
         *
         * @return A list of all {@link Store}.
         */
        List<Store> findAllStores();


}
