package com.lab.studentmoney_gateway.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String STUDENT_QUEUE = "user.create.student";
    public static final String COMPANY_QUEUE = "user.create.company";

    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT_QUEUE, true);
    }

    @Bean
    public Queue companyQueue() {
        return new Queue(COMPANY_QUEUE, true);
    }
}
