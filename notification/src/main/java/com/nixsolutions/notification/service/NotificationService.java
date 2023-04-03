package com.nixsolutions.notification.service;

import com.nixsolutions.clients.notification.NotificationRequest;
import com.nixsolutions.notification.entity.Notification;
import com.nixsolutions.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest request) {
        notificationRepository.save(
            Notification.builder()
                .toCustomerId(request.getToCustomerId())
                .toCustomerEmail(request.getToCustomerEmail())
                .message(request.getMessage())
                .sender("NIX SOLUTIONS")
                .sentAt(LocalDateTime.now())
                .build()
        );
    }
}
