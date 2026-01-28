package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.packet.Packet;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import com.vttish.minescrap.core.network.NetworkClient;
import com.vttish.minescrap.core.network.PacketListener;

public class MCProtocolNetworkClient1_16_5 implements NetworkClient {
    private Client client;
    private PacketListener packetListener;
    private final String username;
    private final String host;
    private final int port;

    public MCProtocolNetworkClient1_16_5(String username, String host, int port) {
        this.username = username;
        this.host = host;
        this.port = port;
        createClient();
    }

    @Override
    public void connect() {
        if (client != null && isConnected()) {
            disconnect();
        }

        createClient();
        setPacketListener(packetListener);

        client.getSession().connect();
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

        if (!isConnected()) {
            throw new IllegalArgumentException(
                    "[MCProtocolNetworkClient]: Cannot send packet, client is not connected"
            );
        }

        client.getSession().send((Packet) packet);
    }

    @Override
    public boolean isConnected() {
        return client.getSession().isConnected();
    }

    @Override
    public void setPacketListener(PacketListener listener) {
        this.packetListener = listener;
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

    private void createClient() {
        MinecraftProtocol protocol = new MinecraftProtocol(username);
        client = new Client(host, port, protocol, new TcpSessionFactory());
    }
}
