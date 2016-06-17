package net.tajzich.boxcar.client;

import feign.Feign;

/**
 * Created by vtajzich
 */
public class BoxcarClient {

    private final String accessToken;
    private final Boxcar boxcar;

    public BoxcarClient(String accessToken) {
        this(accessToken, "https://new.boxcar.io");
    }

    public BoxcarClient(String accessToken, String url) {
        this.accessToken = accessToken;

        boxcar = Feign.builder()
                .encoder(new BoxcarEncoder())
                .target(Boxcar.class, url);
    }

    void send(Notification notification) {
        boxcar.publishNotification(new NotificationRequest(accessToken, notification));
    }
}
