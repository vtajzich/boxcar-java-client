# boxcar-java-client

[![Build Status](https://travis-ci.org/vtajzich/boxcar-java-client.svg?branch=master)](https://travis-ci.org/vtajzich/boxcar-java-client)

Java client for [boxcar.io](https://boxcar.io) (rest api described [here](http://help.boxcar.io/support/solutions/articles/6000004813-how-to-send-a-notification-to-boxcar-for-ios-users))

## How to use it

```
BoxcarClient client = new BoxcarClient("access_token")

Notification notification = new Notification(title, longMessage, sound, sourceName, iconUrl, openUrl)

client.send(notification)
```
