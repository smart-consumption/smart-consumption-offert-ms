package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryEmbeddable {
    private  String categoryName;
}
