package com.unicauca.smart_consumption.domain.review.ports.out;

import com.unicauca.smart_consumption.domain.review.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewRepository {
    /**
     * Creates a new review in the system.
     *
     * @param review The {@link Review} to be created.
     * @return The created {@link Review}.
     */
    Review createReview(Review review);

    /**
     * Updates an existing review in the system.
     *
     * @param id The ID of the {@link Review} to be updated.
     * @param review The {@link Review} with the updated information.
     * @return The updated {@link Review}.
     */
    Review updateReview(String id, Review review);

    /**
     * Deletes an existing review from the system.
     *
     * @param id The ID of the {@link Review} to be deleted.
     */
    void deleteReview(String id);

    /**
     * Finds a review in the system by its ID.
     *
     * @param id The ID of the {@link Review} to be retrieved.
     * @return An {@link Optional} containing the found {@link Review}, if it exists, or {@link Optional#empty()} if not found.
     */
    Optional<Review> findReviewById(String id);

    /**
     * Retrieves a list of all reviews in the system.
     *
     * @return A list of all {@link Review}.
     */
    List<Review> findAllReviews();

}
