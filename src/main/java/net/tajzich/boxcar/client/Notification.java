package net.tajzich.boxcar.client;

import java.net.URL;

/**
 * Created by vtajzich
 */
public class Notification {

    private final String title;
    private final String longMessage;
    private final Sound sound;
    private final String sourceName;
    private final URL iconUrl;
    private final URL openUrl;

    public Notification(String title, String longMessage) {
        this(title, longMessage, null, null, null, null);
    }
    
    public Notification(String title, String longMessage, Sound sound) {
        this(title, longMessage, sound, null, null, null);
    }
    
    public Notification(String title, String longMessage, Sound sound, String sourceName) {
        this(title, longMessage, sound, sourceName, null, null);
    }
    
    public Notification(String title, String longMessage, Sound sound, String sourceName, URL iconUrl) {
        this(title, longMessage, sound, sourceName, iconUrl, null);
    }

    public Notification(String title, String longMessage, Sound sound, String sourceName, URL iconUrl, URL openUrl) {
        this.title = title;
        this.longMessage = longMessage;
        this.sound = sound;
        this.sourceName = sourceName;
        this.iconUrl = iconUrl;
        this.openUrl = openUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public Sound getSound() {
        return sound;
    }

    public String getSourceName() {
        return sourceName;
    }

    public URL getIconUrl() {
        return iconUrl;
    }

    public URL getOpenUrl() {
        return openUrl;
    }
}
