package com.unicauca.smart_consumption.infrastructure.modules.offer.dataproviders.jpa;

import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import com.unicauca.smart_consumption.infrastructure.modules.store.dataproviders.jpa.StoreJPAEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "offer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OfferJPAEntity {
  @Id
  private String id;
  private String description;

  @Embedded
  private PeriodEmbeddable period;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "product_id")
  @ToString.Exclude
  private ProductJpaEntity product;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "store_id")
  @ToString.Exclude
  private StoreJPAEntity store;

  private double discountPercentage;

  private double discountedPrice;
  @PrePersist
  public void prePersist() {
    if (Objects.isNull(this.id)  || this.id.isEmpty()) {
      this.id = UUID.randomUUID().toString();
    }
  }
}
