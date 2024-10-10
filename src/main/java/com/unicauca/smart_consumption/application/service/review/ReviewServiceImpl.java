package com.unicauca.smart_consumption.application.service.review;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductQueryService;
import com.unicauca.smart_consumption.domain.review.Review;
import com.unicauca.smart_consumption.domain.review.ports.in.IReviewService;
import com.unicauca.smart_consumption.domain.review.ports.out.IReviewRepository;
import com.unicauca.smart_consumption.domain.user.ports.out.IUserRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewServiceImpl implements IReviewService {

    private final IReviewRepository reviewRepository;
    private final IProductQueryService productQueryService;
    private final IUserRepository userRepository;
    

    @Override
    public ResponseDto<Review> createReview(Review review, String userId, String productId) {
        review.setUser(userRepository.findUserById(userId).orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                MessageLoader.getInstance().getMessage(MessagesConstant.EM002, userId))));
        review.setProduct(productQueryService.findProductById(productId).getData());
        Review createdReview = reviewRepository.createReview(review);
        return new ResponseDto<>(HttpStatus.CREATED.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002), createdReview);
    }

    @Override
    public ResponseDto<Review> updateReview(String id, Review review) {
        Review updatedReview = reviewRepository.updateReview(id, review);
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM003), updatedReview);
    }

    @Override
    public ResponseDto<Void> deleteReview(String id) {
        if (reviewRepository.findReviewById(id).isEmpty()) {
            reviewRepository.deleteReview(id);
            return new ResponseDto<>(HttpStatus.NO_CONTENT.value(),
                    MessageLoader.getInstance().getMessage(MessagesConstant.IM004));
        } else {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                    MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id));
        }
    }

    @Override
    public ResponseDto<Review> findReviewById(final String id) {
        Review review = reviewRepository.findReviewById(id)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                        MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id)));
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), review);
    }

    @Override
    public ResponseDto<List<Review>> findAllReviews() {
        List<Review> reviews = reviewRepository.findAllReviews();
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), reviews);
    }

}
