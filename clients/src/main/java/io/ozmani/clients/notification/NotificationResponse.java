package io.ozmani.clients.notification;

import java.time.LocalDateTime;

public record NotificationResponse(
        String message,
        Long userid,
        LocalDateTime localDateTime
) {
}
