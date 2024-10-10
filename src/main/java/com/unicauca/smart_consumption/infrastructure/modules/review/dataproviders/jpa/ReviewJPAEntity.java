package com.unicauca.smart_consumption.infrastructure.modules.review.dataproviders.jpa;

import com.unicauca.smart_consumption.domain.product.Rating;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import com.unicauca.smart_consumption.infrastructure.modules.user.dataproviders.jpa.UserJPAEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewJPAEntity {
  @Id
  private String id;

  private String comment;

  private LocalDateTime date;

  @Enumerated(EnumType.STRING)
  private Rating rating;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "product_id", nullable = false)
  private ProductJpaEntity product;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "user_id", nullable = false)
  private UserJPAEntity user;

  @PrePersist
  public void prePersist() {
    if (Objects.isNull(this.id)  || this.id.isEmpty()) {
      this.id = UUID.randomUUID().toString();
    }
  }
}
