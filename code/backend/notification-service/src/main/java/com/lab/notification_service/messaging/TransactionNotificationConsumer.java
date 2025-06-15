package com.lab.notification_service.messaging;

import com.lab.notification_service.model.NotificationChannel;
import com.lab.notification_service.model.NotificationRequest;
import com.lab.notification_service.model.NotificationType;
import com.lab.notification_service.model.TransactionNotificationMessage;
import com.lab.notification_service.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionNotificationConsumer {

    private final NotificationService notificationService;

    public TransactionNotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.transaction-notification}")
    public void consumeTransactionNotification(TransactionNotificationMessage message) {
        NotificationRequest request = new NotificationRequest();
        request.setRecipientId(message.getUserId());
        request.setRecipientEmail(message.getUserEmail());
        request.setRecipientPhone(message.getUserPhone());
        request.setType(NotificationType.TRANSACTION);
        request.setChannel(NotificationChannel.EMAIL);
        request.setSubject("Nova Transação Realizada");
        request.setContent(String.format(
            "Uma nova transação foi realizada:\n" +
            "Tipo: %s\n" +
            "Valor: R$ %.2f\n" +
            "Descrição: %s",
            message.getTransactionType(),
            message.getAmount(),
            message.getDescription()
        ));

        notificationService.sendNotification(request);
    }
} 