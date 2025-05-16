package com.lab.user_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

@Service
public class TwoFactorAuthService {

    private final RedisTemplate<String, String> redisTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Value("${2fa.expiration.minutes:5}")
    private int expirationMinutes;

    public TwoFactorAuthService(RedisTemplate<String, String> redisTemplate, RabbitTemplate rabbitTemplate) {
        this.redisTemplate = redisTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send2FACode(String userId, String destination, String channel, String purpose) {
        String code = String.format("%06d", new Random().nextInt(999999));
        String redisKey = String.format("2fa:%s:%s", userId, purpose);

        // Armazena no Redis com expiração
        redisTemplate.opsForValue().set(redisKey, code, Duration.ofMinutes(expirationMinutes));

        // Envia para fila
        Map<String, String> message = Map.of(
                "userId", userId,
                "destination", destination,
                "channel", channel,
                "message", "Seu código de verificação é: " + code,
                "purpose", purpose
        );

        rabbitTemplate.convertAndSend("notification.exchange", "notification.2fa", message);
    }

    public boolean validate2FACode(String userId, String code, String purpose) {
        String redisKey = String.format("2fa:%s:%s", userId, purpose);
        String storedCode = redisTemplate.opsForValue().get(redisKey);
        return code.equals(storedCode);
    }
}

