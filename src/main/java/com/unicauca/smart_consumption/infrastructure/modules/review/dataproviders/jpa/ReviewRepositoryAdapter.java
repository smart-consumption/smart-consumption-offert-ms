package com.unicauca.smart_consumption.infrastructure.modules.review.dataproviders.jpa;

import com.unicauca.smart_consumption.domain.review.Review;
import com.unicauca.smart_consumption.domain.review.ports.out.IReviewRepository;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ReviewJPAMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewRepositoryAdapter implements IReviewRepository {

    private final ReviewJPARepository reviewJPARepository;
    private final ReviewJPAMapper reviewJPAMapper;


    @Override
    public Review createReview(Review review) {
        ReviewJPAEntity entity = reviewJPAMapper.toTarget(review);
        return reviewJPAMapper.toDomain(reviewJPARepository.save(entity));
    }

    @Override
    public Review updateReview(String id, Review review) {
        return reviewJPARepository.findById(id)
                .map(reviewEntity -> {
                    Review domainReview = new Review();
                    domainReview.setProduct(review.getProduct());
                    domainReview.setUser(review.getUser());
                    domainReview.updateReview(review.getRating(), review.getComment());
                    domainReview.setDate(review.getDate());
                    ReviewJPAEntity updatedEntity = reviewJPAMapper.toTarget(domainReview);
                    reviewJPARepository.save(updatedEntity);
                    return domainReview;
                })
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id " + id));
    }


    @Override
    public void deleteReview(String id) {
        if (reviewJPARepository.existsById(id)) {
            reviewJPARepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Review not found with id " + id);
        }
    }


    @Override
    public Optional<Review> findReviewById(String id) {
        return reviewJPARepository.findById(id).map(
                entity -> {
                    Review domainEntity = new Review();
                    domainEntity.setId(entity.getId());
                    domainEntity.setRating(entity.getRating());
                    domainEntity.setComment(entity.getComment());
                    domainEntity.setDate(entity.getDate());
                    return domainEntity;
                }
        );
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewJPARepository.findAll().stream()
                .map(entity ->{
                    Review domainEntity = new Review();
                    domainEntity.setId(entity.getId());
                    domainEntity.setRating(entity.getRating());
                    domainEntity.setComment(entity.getComment());
                    domainEntity.setDate(entity.getDate());
                    return domainEntity;
                }
                ).toList();
    }
}
