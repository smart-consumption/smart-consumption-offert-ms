package com.unicauca.smart_consumption.domain.offer.ports.in;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.offer.Offer;

import java.util.List;

public interface IOfferService {
    /**
     * Creates a new offer in the system.
     *
     * @param offer The {@link Offer} to be created.
     * @return A {@link ResponseDto} containing the created {@link Offer} object and an HTTP status code.
     */
    ResponseDto<Offer> createOffer(Offer offer, String storeId, String productId);

    /**
     * Updates an existing offer in the system.
     *
     * @param id The ID of the {@link Offer} to be updated.
     * @param offer The {@link Offer} with updated information.
     * @return A {@link ResponseDto} containing the updated {@link Offer} object and an HTTP status code.
     */
    ResponseDto<Offer> updateOffer(String id, Offer offer);

    /**
     * Deletes an existing offer from the system.
     *
     * @param id The ID of the {@link Offer} to be deleted.
     * @return A {@link ResponseDto} indicating the result of the operation with an HTTP status code.
     */
    ResponseDto<Void> deleteOffer(String id);

    /**
     * Finds an offer in the system by its ID.
     *
     * @param id The ID of the {@link Offer} to be retrieved.
     * @return A {@link ResponseDto} containing the found {@link Offer} if it exists, and an HTTP status code.
     */
    ResponseDto<Offer> findOfferById(String id);

    /**
     * Retrieves a list of all offers in the system.
     *
     * @return A {@link ResponseDto} containing the list of all {@link Offer} objects and an HTTP status code.
     */
    ResponseDto<List<Offer>> findAllOffers();
}
