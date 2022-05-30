package io.ozmani.notification;

import io.ozmani.clients.notification.NotificationRequest;
import io.ozmani.clients.notification.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationResponse createNotification(NotificationRequest request) {

        NotificationEntity notification = new NotificationEntity();
        notification.setCreatedAt(LocalDateTime.now());
        notification.setMessage(request.message());
        notification.setUserId(request.userId());
        notification.setEmail(request.userEmail());
        notificationRepository.save(notification);

        // Send notification here -

        // return response.
        return new NotificationResponse(request.message(), request.userId(), notification.getCreatedAt());
    }
}
