package com.unicauca.smart_consumption_offert_ms.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration
public class RabbitMQConfig {

    public static final String OFFER_CREATED_QUEUE = "offer.created.queue";
    public static final String OFFER_UPDATED_QUEUE = "offer.updated.queue";
    public static final String  OFFER_EXCHANGE = "offer.exchange";
    public static final String ROUTING_KEY_OFFER_CREATED = "offer.created";
    public static final String ROUTING_KEY_OFFER_UPDATED = "offer.updated";

    public static final String PRODUCT_CREATED_QUEUE = "product.created.queue";
    public static final String PRODUCT_UPDATED_QUEUE = "product.updated.queue";
    public static final String ROUTING_KEY_PRODUCT_CREATED = "product.created";
    public static final String ROUTING_KEY_PRODUCT_UPDATED = "product.updated";
    public static final String PRODUCT_EXCHANGE = "product.exchange";

    public static final String USER_EXCHANGE = "user.exchange";
    public static final String USER_CREATED_QUEUE = "user.created.queue";
    public static final String USER_UPDATED_QUEUE = "user.updated.queue";
    public static final String ROUTING_KEY_USER_CREATED = "user.created";
    public static final String ROUTING_KEY_USER_UPDATED = "user.updated";


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Queue userCreatedQueue() {
        return new Queue(USER_CREATED_QUEUE, true);
    }

    @Bean
    Queue userUpdatedQueue() {
        return new Queue(USER_UPDATED_QUEUE, true);
    }

    @Bean
    public Queue offerCreatedQueue() {
        return new Queue(OFFER_CREATED_QUEUE, true);
    }

    @Bean
    public Queue productCreatedQueue() {
        return new Queue(PRODUCT_CREATED_QUEUE, true);
    }

    @Bean
    public Queue productUpdatedQueue() {
        return new Queue(PRODUCT_UPDATED_QUEUE, true);
    }
    @Bean
    public Queue offerUpdatedQueue() {
        return new Queue(OFFER_UPDATED_QUEUE, true);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue("notifications.queue", true);
    }

    @Bean
    public TopicExchange offerExchange() {
        return new TopicExchange(OFFER_EXCHANGE);
    }

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public Binding bindingCreated() {
        return BindingBuilder.bind(offerCreatedQueue()).to(offerExchange())
                .with(ROUTING_KEY_OFFER_CREATED);
    }

    @Bean
    public Binding bindingUpdated() {
        return BindingBuilder.bind(offerUpdatedQueue()).to(offerExchange()).
                with(ROUTING_KEY_OFFER_UPDATED);
    }

    @Bean
    public Binding bindingProductCreated() {
        return BindingBuilder.bind(productCreatedQueue()).to(productExchange())
                .with(ROUTING_KEY_PRODUCT_CREATED);
    }

    @Bean
    public Binding bindingProductUpdated() {
        return BindingBuilder.bind(productUpdatedQueue()).to(productExchange())
                .with(ROUTING_KEY_PRODUCT_UPDATED);
    }

    @Bean
    Binding bindingUserCreated() {
        return BindingBuilder.bind(userCreatedQueue()).
                to(userExchange()).with(ROUTING_KEY_USER_CREATED);
    }

    @Bean
    Binding bindingUserUpdated() {
        return BindingBuilder.bind(userUpdatedQueue()).
                to(userExchange()).with(ROUTING_KEY_USER_UPDATED);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
