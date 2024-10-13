package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.command.sql;

import com.unicauca.smart_consumption_offert_ms.domain.product.ProductStatus;
import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.common.SustainabilityCriteriaEmbeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductJpaEntity {
    @Id
    private String id;

    private String name;
    @Embedded
    private CategoryEmbeddable category;

    @Embedded
    private DetailEmbeddable detail;

    @Embedded
    private SustainabilityCriteriaEmbeddable sustainabilityCriteria;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private double price;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(this.id)  || this.id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
