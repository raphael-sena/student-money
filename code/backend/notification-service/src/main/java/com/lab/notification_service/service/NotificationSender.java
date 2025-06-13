package com.lab.notification_service.service;

import com.lab.notification_service.model.NotificationRequest;

public interface NotificationSender {
    void send(NotificationRequest request);
    boolean supports(NotificationRequest request);
} 