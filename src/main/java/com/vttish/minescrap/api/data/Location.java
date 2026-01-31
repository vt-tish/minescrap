package com.vttish.minescrap.api.data;

public class Location {
    public final double x;
    public final double y;
    public final double z;

    public final float pitch;
    public final float yaw;

    public Location() {
        x = 0;
        y = 0;
        z = 0;
        pitch = 0;
        yaw = 0;
    }

    public Location(double x, double y, double z, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public String toString() {
        return "[X: " + x + "] [Y: " + y + "] [Z: " + z + "] [PITCH: " + pitch + "] [YAW: " + yaw + "]";
    }
}
