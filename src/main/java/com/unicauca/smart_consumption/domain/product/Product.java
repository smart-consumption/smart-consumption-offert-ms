package com.unicauca.smart_consumption.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unicauca.smart_consumption.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
  private String id;
  private String name;
  private Category category;
  private Detail detail;
  private SustainabilityCriteria sustainabilityCriteria;
  private ProductStatus status;
  private double price;
  private List<Review> reviews;
 // private List<Store> stores;

  public void updateProduct(String name, Category category, Detail detail,
                            SustainabilityCriteria sustainabilityCriteria, ProductStatus status, double price) {
    if (name != null && !name.trim().isEmpty()) {
      this.name = name;
    }
    if (category != null) {
      this.category = category;
    }
    if (detail != null) {
      this.detail = detail;
    }
    if (sustainabilityCriteria != null) {
      this.sustainabilityCriteria = sustainabilityCriteria;
    }
    if (status != null) {
      this.status = status;
    }
    if (price > 0) {
      this.price = price;
    }
  }

//  public void addStore(Store store) {
//    if (Objects.nonNull(store) && !stores.contains(store)) {
//      stores.add(store);
//    }
//  }

//  public void removeStore(Store store) {
//    stores.remove(store);
//  }
//
//  public boolean isAvailableInStore(Store store) {
//    return stores.contains(store);
//  }

//  public List<Store> getStoresByCity(City city) {
//    List<Store> storesByCity = new ArrayList<>();
//    for (Store store : stores) {
//      if (store.getCity().equals(city)) {
//        storesByCity.add(store);
//      }
//    }
//    return storesByCity;
//  }

  public void applyDiscount(double percentage) {
    if (percentage > 0 && percentage <= 100) {
      this.price -= this.price * (percentage / 100);
    }
  }
//
//  public double calculateAverageRating() {
//    if (reviews.isEmpty()) {
//      return 0.0;
//    }
//    double sum = 0.0;
//    for (Review review : reviews) {
//      sum += review.getRating().getValue();
//    }
//    return sum / reviews.size();
//  }
//
//  public List<Review> getReviewsSortedByDate() {
//    List<Review> sortedReviews = new ArrayList<>(reviews);
//    sortedReviews.sort(Comparator.comparing(Review::getDate));
//    return sortedReviews;
//  }

  public boolean isSustainable() {
    return this.sustainabilityCriteria.getSustainabilityScore() > 75;
  }

  public boolean isInCategory(Category category) {
    return this.category.equals(category);
  }

  public Product orElseThrow(Object object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
  }
}
