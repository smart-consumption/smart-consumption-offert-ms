package com.unicauca.smart_consumption.domain.user;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String username;
    private String name;
   // private List<Review> reviews;
    private ArrayList<Product> watchList;
    private City city;

    
    public void updateUser(String username, String name, City city) {
        if (username != null && !username.trim().isEmpty()) {
            this.name = name;
        }
        
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (city != null) {
            this.city = city;
        }
    }

//    public void addReview(Review review) {
//        if (Objects.nonNull(review) && !reviews.contains(review)) {
//            reviews.add(review);
//        }
//    }
//
//    public void removeReview(Review review) {
//        reviews.remove(review);
//    }
//
//    public boolean hasReviewedProduct(Product product) {
//        return reviews.stream().anyMatch(review -> review.isForProduct(product));
//    }
//
//    public boolean hasPositiveReviews() {
//        return reviews.stream().anyMatch(Review::isPositive);
//    }

//    public boolean isReviewAuthentic(Review review) {
//        return reviews.contains(review) && review.isAuthentic();
//    }
    
    public boolean addProductToWatchList(Product product)
    {
        if (Objects.nonNull(product) && !watchList.contains(product))
        {
            watchList.add(product);
            return true;
        }
        return false;
    }

    public boolean deleteProductFromWatchList(Product product)
    {
        if (Objects.nonNull(product) && watchList.contains(product))
        {
            watchList.remove(product);
            return true;
        }
        return false;
    }
}
