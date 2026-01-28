package com.vttish.minescrap.core.network;

public interface PacketListener {
    void onPackedReceived(Object packet);
    default void onDisconnected(String reason) {}
}
