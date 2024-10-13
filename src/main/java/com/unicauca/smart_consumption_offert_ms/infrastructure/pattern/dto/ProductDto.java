package com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.dto;

import com.unicauca.smart_consumption_offert_ms.domain.product.Detail;
import com.unicauca.smart_consumption_offert_ms.domain.product.ProductStatus;
import com.unicauca.smart_consumption_offert_ms.domain.product.SustainabilityCriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
  private String id;
  private String name;
  private CategoryDto category;
  private Detail detail;
  private SustainabilityCriteria sustainabilityCriteria;
  private ProductStatus status;
  private double price;
}
