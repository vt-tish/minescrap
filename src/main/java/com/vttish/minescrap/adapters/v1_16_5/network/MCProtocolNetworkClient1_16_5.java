package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.packet.Packet;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import com.vttish.minescrap.core.network.NetworkClient;

public class MCProtocolNetworkClient1_16_5 implements NetworkClient {
    private final Client client;

    public MCProtocolNetworkClient1_16_5(String username, String host, int port) {
        MinecraftProtocol protocol = new MinecraftProtocol(username);

        this.client = new Client(host, port, protocol, new TcpSessionFactory());
    }

    @Override
    public void connect() {
        this.client.getSession().connect();
    }

    @Override
    public void disconnect() {
        disconnect("quit");
    }

    @Override
    public void disconnect(String reason) {
        if (client.getSession().isConnected()) {
            client.getSession().disconnect(reason);
        }
    }

    @Override
    public void sendPacket(Object packet) {
        if (!(packet instanceof Packet)) {
            throw new IllegalArgumentException("[MCProtocolNetworkClient]: Object should be an instance of Packet");
        }

        client.getSession().send((Packet) packet);
    }

    @Override
    public boolean isConnected() {
        return client.getSession().isConnected();
    }

    @Override
    public void setPacketListener(PacketListener listener) {
        client.getSession().addListener(new SessionAdapter() {
            @Override
            public void packetReceived(PacketReceivedEvent event) {
                listener.onPackedReceived(event.getPacket());
            }

            @Override
            public void disconnected(DisconnectedEvent event) {
                listener.onDisconnected(event.getReason());
            }
        });
    }
}
