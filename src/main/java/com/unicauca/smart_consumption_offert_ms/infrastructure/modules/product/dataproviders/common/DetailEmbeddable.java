package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.common;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetailEmbeddable {
    private  String description;
    private  String specifications;
}
