package net.tajzich.boxcar.client;

import feign.RequestLine;

/**
 * Created by vtajzich
 */
public interface Boxcar {

    @RequestLine("POST /api/notifications")
    void publishNotification(NotificationRequest request);
}
