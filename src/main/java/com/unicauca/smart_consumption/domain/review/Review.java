package com.unicauca.smart_consumption.domain.review;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.product.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    private String id;
    private User user;
    private Product product;
    private Rating rating;
    private String comment;
    private LocalDateTime date;



    public boolean isPositive() {
        return this.rating == Rating.GOOD || this.rating == Rating.EXCELLENT;
    }

    public boolean isForProduct(Product product) {
        return this.product.equals(product);
    }

    public boolean isByUser(User user) {
        return this.user.equals(user);
    }

    public void updateReview(Rating newRating, String newComment) {
        if (newRating != null) {
            this.rating = newRating;
        }
        if (newComment != null && !newComment.trim().isEmpty()) {
            this.comment = newComment;
        }
    }

    public boolean isCommentLengthValid() {
        return this.comment.length() >= 10 && this.comment.length() <= 500;
    }

}
