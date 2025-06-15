package com.lab.notification_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.transaction-notification}")
    private String transactionNotificationQueue;

    @Bean
    public Queue transactionNotificationQueue() {
        return new Queue(transactionNotificationQueue, true);
    }
} 