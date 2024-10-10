package com.unicauca.smart_consumption.domain.offer.ports.out;

import com.unicauca.smart_consumption.domain.offer.Offer;

import java.util.List;
import java.util.Optional;

public interface IOfferRepository {
    /**
     * Creates a new offer in the system.
     *
     * @param offer The {@link Offer} to be created.
     * @return The created {@link Offer} object.
     */
    Offer createOffer(Offer offer);

    /**
     * Updates an existing offer in the system.
     *
     * @param id The ID of the {@link Offer} to be updated.
     * @param offer The {@link Offer} with updated information.
     * @return The updated {@link Offer} object.
     */
    Offer updateOffer(String id, Offer offer);

    /**
     * Deletes an existing offer from the system.
     *
     * @param id The ID of the {@link Offer} to be deleted.
     */
    void deleteOffer(String id);

    /**
     * Finds an offer in the system by its ID.
     *
     * @param id The ID of the {@link Offer} to be retrieved.
     * @return An {@link Optional} containing the found {@link Offer} if it exists, or {@link Optional#empty()} if not found.
     */
    Optional<Offer> findOfferById(String id);

    /**
     * Retrieves a list of all offers in the system.
     *
     * @return A list of all {@link Offer} objects.
     */
    List<Offer> findAllOffers();
}
