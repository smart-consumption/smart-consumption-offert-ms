package com.unicauca.smart_consumption.infrastructure.modules.store.dataproviders.jpa;

import com.unicauca.smart_consumption.infrastructure.modules.city.dataproviders.jpa.CityJPAEntity;
import com.unicauca.smart_consumption.infrastructure.modules.offer.dataproviders.jpa.OfferJPAEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StoreJPAEntity {
  @Id
  private String id;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "store_product",
      joinColumns = @JoinColumn(name = "store_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  @ToString.Exclude
  private List<ProductJpaEntity> products;


  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<OfferJPAEntity> offers;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "city_id")
  private CityJPAEntity city;

  @PrePersist
  public void prePersist() {
    if (Objects.isNull(this.id)  || this.id.isEmpty()) {
      this.id = UUID.randomUUID().toString();
    }
  }
}
