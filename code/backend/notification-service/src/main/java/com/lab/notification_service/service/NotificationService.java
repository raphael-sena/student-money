package com.lab.notification_service.service;

import com.lab.notification_service.model.NotificationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    
    private final List<NotificationSender> notificationSenders;

    public NotificationService(List<NotificationSender> notificationSenders) {
        this.notificationSenders = notificationSenders;
    }

    public void sendNotification(NotificationRequest request) {
        notificationSenders.stream()
                .filter(sender -> sender.supports(request))
                .findFirst()
                .ifPresent(sender -> sender.send(request));
    }
} 