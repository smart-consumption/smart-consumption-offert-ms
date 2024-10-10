package com.unicauca.smart_consumption.domain.review.ports.in;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.review.Review;

import java.util.List;

public interface IReviewService {
    /**
     * Creates a new review in the system.
     *
     * @param review The {@link Review} to be created.
     * @return A {@link ResponseDto} containing the created {@link Review} and an HTTP status code.
     */
    ResponseDto<Review> createReview(Review review, String userId, String productId);

    /**
     * Updates an existing review in the system.
     *
     * @param id The ID of the {@link Review} to be updated.
     * @param review The {@link Review} with the updated information.
     * @return A {@link ResponseDto} containing the updated {@link Review} and an HTTP status code.
     */
    ResponseDto<Review> updateReview(String id, Review review);

    /**
     * Deletes an existing review from the system.
     *
     * @param id The ID of the {@link Review} to be deleted.
     * @return A {@link ResponseDto} indicating the result of the operation with an HTTP status code.
     */
    ResponseDto<Void> deleteReview(String id);

    /**
     * Finds a review in the system by its ID.
     *
     * @param id The ID of the {@link Review} to be retrieved.
     * @return A {@link ResponseDto} containing the found {@link Review}, if it exists, and an HTTP status code.
     */
    ResponseDto<Review> findReviewById(String id);

    /**
     * Retrieves a list of all reviews in the system.
     *
     * @return A {@link ResponseDto} containing the list of all {@link Review} and an HTTP status code.
     */
    ResponseDto<List<Review>> findAllReviews();

}
