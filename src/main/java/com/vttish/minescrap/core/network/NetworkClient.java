package com.vttish.minescrap.core.network;

import java.util.UUID;

public interface NetworkClient {
    void connect();
    void disconnect();
    void disconnect(String reason);

    void sendPacket(Object packet);

    boolean isConnected();

    interface PacketListener {
        void onPackedReceived(Object packet);
        void onDisconnected(String reason);
    }
}
