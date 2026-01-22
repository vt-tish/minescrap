package com.vttish.minescrap.core.network;

import com.vttish.minescrap.api.data.Location;

public interface ActionSender {
    void sendLocation(double x, double y, double z, float yaw, float pitch, boolean onGround);
    default void sendLocation(Location location, boolean onGround) {
        sendLocation(
                location.x, location.y, location.z,
                location.yaw, location.pitch,
                onGround
        );
    }

    void sendPosition(double x, double y, double z, boolean onGround);
    void sendRotation(float yaw, float pitch, boolean onGround);
    void sendOnGround(boolean onGround);

    void sendChat(String message);
}
