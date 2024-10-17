package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.offer.publisher;

import static com.unicauca.smart_consumption_offert_ms.infrastructure.config.RabbitMQConfig.ROUTING_KEY_OFFER_CREATED;
import static com.unicauca.smart_consumption_offert_ms.infrastructure.config.RabbitMQConfig.ROUTING_KEY_OFFER_UPDATED;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Offer;
import com.unicauca.smart_consumption_offert_ms.domain.offer.ports.IOfferEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.unicauca.smart_consumption_offert_ms.infrastructure.config.RabbitMQConfig;

@Component
public class OfferPublisher implements IOfferEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OfferPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void publishOffertCreated(Offer offer) {
        System.out.println("Send message create queue: " + offer);
        rabbitTemplate.convertAndSend(RabbitMQConfig.OFFER_EXCHANGE, ROUTING_KEY_OFFER_CREATED , offer);
    }

    @Override
    public void publishOffertUpdated(Offer offer) {
        System.out.println("Send message update queue: " + offer);
        rabbitTemplate.convertAndSend(RabbitMQConfig.OFFER_EXCHANGE, ROUTING_KEY_OFFER_UPDATED , offer);
    }
}
