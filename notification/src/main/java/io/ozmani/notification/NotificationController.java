package io.ozmani.notification;

import io.ozmani.clients.notification.NotificationRequest;
import io.ozmani.clients.notification.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "api/v1/notification/")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse publishNotification(@RequestBody NotificationRequest request) {
        log.info("Notification sent for user: {}", request.userId());
        return notificationService.createNotification(request);
    }
}
