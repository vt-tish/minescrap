package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.packet.Packet;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.network.NetworkClient;

import java.util.UUID;

public class MCProtocolNetworkClient1_16_5 implements NetworkClient {
    private final Client client;
    private final PacketListener packetListener;

    public MCProtocolNetworkClient1_16_5(String username, String host, int port, MinecraftBotImpl minecraftBot) {
        MinecraftProtocol protocol = new MinecraftProtocol(username);

        this.client = new Client(host, port, protocol, new TcpSessionFactory());
        this.packetListener = new PacketListener1_16_5(minecraftBot);

        throwIfSessionIsNotValid();
        client.getSession().addListener(new SessionAdapter() {
            @Override
            public void packetReceived(PacketReceivedEvent event) {
                packetListener.onPackedReceived(event.getPacket());
            }

            @Override
            public void disconnected(DisconnectedEvent event) {
                packetListener.onDisconnected(event.getReason());
            }
        });
    }

    @Override
    public void connect() {
        throwIfSessionIsNotValid();
        this.client.getSession().connect();
    }

    @Override
    public void disconnect() {
        disconnect("quit");
    }

    @Override
    public void disconnect(String reason) {
        throwIfSessionIsNotValid();
        if (client.getSession().isConnected()) {
            client.getSession().disconnect(reason);
        }
    }

    @Override
    public void sendPacket(Object packet) {
        if (!(packet instanceof Packet)) {
            throw new IllegalArgumentException("[MCProtocolNetworkClient]: Object should be an instance of Packet");
        }

        throwIfSessionIsNotValid();
        client.getSession().send((Packet) packet);
    }

    @Override
    public boolean isConnected() {
        throwIfSessionIsNotValid();
        return client.getSession().isConnected();
    }

    private void throwIfSessionIsNotValid() {
        if (client.getSession() == null) {
            throw new NullPointerException("[MCProtocolNetworkClient]: Client or client session is null");
        }
    }

}
