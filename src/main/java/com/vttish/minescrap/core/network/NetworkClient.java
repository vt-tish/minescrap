package com.vttish.minescrap.core.network;

public interface NetworkClient {
    void connect();
    void disconnect();
    void disconnect(String reason);

    void sendPacket(Object packet);

    boolean isConnected();

    void setPacketListener(PacketListener listener);
}
