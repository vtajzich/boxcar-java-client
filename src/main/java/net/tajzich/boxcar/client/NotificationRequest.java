package net.tajzich.boxcar.client;

/**
 * Created by vtajzich
 */
public class NotificationRequest {

    private final String accessToken;
    private final Notification notification;

    public NotificationRequest(String accessToken, Notification notification) {
        this.accessToken = accessToken;
        this.notification = notification;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Notification getNotification() {
        return notification;
    }
}
