package com.unicauca.smart_consumption_offert_ms.domain.offer.ports;

import com.unicauca.smart_consumption_offert_ms.domain.offer.Offer;

public interface IOfferEventPublisher {

    void publishOffertCreated(Offer offer);

    void publishOffertUpdated(Offer offer);
}
