package com.unicauca.smart_consumption.infrastructure.modules.review.api;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.review.Review;
import com.unicauca.smart_consumption.domain.review.ports.in.IReviewService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ReviewDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ReviewMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Review APIs", description = "Review web APIs")
public class ReviewWebApi {
    private final IReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<ResponseDto<ReviewDto>> createReview(
            @PathVariable String userId,
            @PathVariable String productId,
            @RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.toDomain(reviewDto);
        ResponseDto<Review> reviewResponse = reviewService.createReview(review,userId,productId);
        ReviewDto createdReviewDto = reviewMapper.toTarget(reviewResponse.getData());

        return new ResponseDto<>(reviewResponse.getStatus(),
                reviewResponse.getMessage(), createdReviewDto).of();
    }

    @PutMapping("/{entityId}")
    public ResponseEntity<ResponseDto<ReviewDto>> updateReview(@PathVariable String entityId, @RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.toDomain(reviewDto);
        ResponseDto<Review> reviewResponse = reviewService.updateReview(entityId, review);
        ReviewDto updatedReviewDto = reviewMapper.toTarget(reviewResponse.getData());
        return  new ResponseDto<>(reviewResponse.getStatus(),
                reviewResponse.getMessage(), updatedReviewDto).of();
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<ResponseDto<Void>> deleteReview(@PathVariable String entityId) {
        ResponseDto<Void> reviewResponse=  reviewService.deleteReview(entityId);
        return new ResponseDto<Void>(reviewResponse.getStatus(),reviewResponse.getMessage()).of();
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto<ReviewDto>> getReviewById(@PathVariable String entityId){
        ResponseDto<Review> reviewResponse = reviewService.findReviewById(entityId);
        ReviewDto reviewDto = reviewMapper.toTarget(reviewResponse.getData());
        ResponseDto<ReviewDto> reviewDtoResponse = new ResponseDto<>(reviewResponse.getStatus(), reviewResponse.getMessage(), reviewDto);
        return reviewDtoResponse.of();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ReviewDto>>> getAllReviews(){
        ResponseDto<List<Review>> reviewResponse = reviewService.findAllReviews();
        return new ResponseDto<>(
                reviewResponse.getStatus(),
                reviewResponse.getMessage(),
                reviewResponse.getData().stream().map(reviewMapper::toTarget).toList()
        ).of();
    }

}
