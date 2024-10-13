package com.unicauca.smart_consumption_offert_ms.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
  private String id;
  private String name;
  private ProductStatus status;
  private double price;

  public void applyDiscount(double percentage) {
    if (percentage > 0 && percentage <= 100) {
      this.price -= this.price * (percentage / 100);
    }
  }
}
