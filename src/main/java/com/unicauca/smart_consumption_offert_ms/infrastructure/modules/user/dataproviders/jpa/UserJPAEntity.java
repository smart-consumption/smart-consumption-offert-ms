package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.user.dataproviders.jpa;


import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "user_app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserJPAEntity {

  @Id
  private String id;
  private String username;
  private String name;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(
      name = "user_product",
      joinColumns = @JoinColumn(name = "id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<ProductJpaEntity> watchList;



}
