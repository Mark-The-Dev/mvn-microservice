package io.ozmani.clients.notification;

public record NotificationRequest( long userId,
                                   String message,
                                   String userEmail) {

}
