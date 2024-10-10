package com.unicauca.smart_consumption.domain.city.ports.out;

import com.unicauca.smart_consumption.domain.city.City;

import java.util.List;
import java.util.Optional;

public interface ICityRepository {
    /**
     * Creates a new city in the system.
     *
     * @param city The {@link City} to be created.
     * @return The created {@link City} object.
     */
    City createCity(City city);

    /**
     * Updates an existing city in the system.
     *
     * @param id The ID of the {@link City} to be updated.
     * @param city The {@link City} with updated information.
     * @return The updated {@link City} object.
     */
    City updateCity(String id, City city);

    /**
     * Deletes an existing city from the system.
     *
     * @param id The ID of the {@link City} to be deleted.
     */
    void deleteCity(String id);

    /**
     * Finds a city in the system by its ID.
     *
     * @param id The ID of the {@link City} to be retrieved.
     * @return An {@link Optional} containing the found {@link City} if it exists, or {@link Optional#empty()} if not found.
     */
    Optional<City> findCityById(String id);

    /**
     * Retrieves a list of all cities in the system.
     *
     * @return A list of all {@link City} objects.
     */
    List<City> findAllCities();
}
