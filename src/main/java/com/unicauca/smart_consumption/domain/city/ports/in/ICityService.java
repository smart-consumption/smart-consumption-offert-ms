package com.unicauca.smart_consumption.domain.city.ports.in;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.common.ResponseDto;

import java.util.List;

public interface ICityService {
    /**
     * Creates a new city in the system.
     *
     * @param city The {@link City} to be created.
     * @return A {@link ResponseDto} containing the created {@link City} object and an HTTP status code.
     */
    ResponseDto<City> createCity(City city);

    /**
     * Updates an existing city in the system.
     *
     * @param id The ID of the {@link City} to be updated.
     * @param city The {@link City} with updated information.
     * @return A {@link ResponseDto} containing the updated {@link City} object and an HTTP status code.
     */
    ResponseDto<City> updateCity(String id, City city);

    /**
     * Deletes an existing city from the system.
     *
     * @param id The ID of the {@link City} to be deleted.
     * @return A {@link ResponseDto} indicating the result of the operation with an HTTP status code.
     */
    ResponseDto<Void> deleteCity(String id);

    /**
     * Finds a city in the system by its ID.
     *
     * @param id The ID of the {@link City} to be retrieved.
     * @return A {@link ResponseDto} containing the found {@link City} if it exists, and an HTTP status code.
     */
    ResponseDto<City> findCityById(String id);

    /**
     * Retrieves a list of all cities in the system.
     *
     * @return A {@link ResponseDto} containing the list of all {@link City} objects and an HTTP status code.
     */
    ResponseDto<List<City>> findAllCities();
}
