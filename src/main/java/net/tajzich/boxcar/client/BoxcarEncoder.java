package net.tajzich.boxcar.client;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;

/**
 * Created by vtajzich
 */
public class BoxcarEncoder implements Encoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {

        if (object instanceof NotificationRequest) {
            template.body(convert((NotificationRequest) object));
        } else {
            throw new IllegalArgumentException("Object '" + object.getClass() + "' is not supported! Only '" + NotificationRequest.class.getName() + "' is supported.");
        }
    }

    private String convert(NotificationRequest request) {

        Notification notification = request.getNotification();

        if (notification == null) {
            throw new IllegalArgumentException("Notification object cannot be null!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("user_credentials=").append(request.getAccessToken());
        sb.append("&notification[title]=").append(encode(notification.getTitle()));
        sb.append("&notification[long_message]=").append(encode(notification.getLongMessage()));

        if (notification.getSound() != null) {
            sb.append("&notification[sound]=").append(encode(notification.getSound().getValue()));
        }

        if (notification.getSourceName() != null) {
            sb.append("&notification[source_name]=").append(encode(notification.getSourceName()));
        }

        if (notification.getIconUrl() != null) {
            sb.append("&notification[icon_url]=").append(encode(notification.getIconUrl().toString()));
        }

        if (notification.getOpenUrl() != null) {
            sb.append("&notification[open_url]=").append(encode(notification.getOpenUrl().toString()));
        }

        return sb.toString();
    }

    private String encode(Object value) {

        try {
            return URLEncoder.encode(String.valueOf(value), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is not supported encoding?!", e);
        }

    }
}
