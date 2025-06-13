package com.lab.notification_service.service;

import com.lab.notification_service.model.NotificationChannel;
import com.lab.notification_service.model.NotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender {
    
    private final JavaMailSender mailSender;

    public EmailNotificationSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(NotificationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getRecipientEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getContent());
        mailSender.send(message);
    }

    @Override
    public boolean supports(NotificationRequest request) {
        return request.getChannel() == NotificationChannel.EMAIL;
    }
} 