package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.data.game.entity.player.HandPreference;
import com.github.steveice10.mc.protocol.data.game.setting.ChatVisibility;
import com.github.steveice10.mc.protocol.data.game.setting.SkinPart;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientPluginMessagePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.login.server.LoginSuccessPacket;
import com.vttish.minescrap.core.network.NetworkClient;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.ConnectionService;

import java.util.List;

public class ConnectionListener1_16_5 implements PacketListener {
    private final NetworkClient networkClient;
    private final ConnectionService connectionService;

    public ConnectionListener1_16_5(NetworkClient networkClient, ConnectionService connectionService) {
        this.networkClient = networkClient;
        this.connectionService = connectionService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerConfirmTransactionPacket p) {
            networkClient.sendPacket(new ClientConfirmTransactionPacket(
                    p.getWindowId(),
                    p.getActionId(),
                    p.isAccepted())
            );
        } else if (packet instanceof ServerJoinGamePacket p) {
            handleJoinGame(p);
        } else if (packet instanceof LoginSuccessPacket p) {
            connectionService.handleLogin(p.getProfile().getName(), p.getProfile().getId());
        }
    }

    private void handleJoinGame(ServerJoinGamePacket packet) {
        connectionService.handleJoinGame(packet.getEntityId());

        networkClient.sendPacket(new ClientSettingsPacket(
                "en_us",
                2,
                ChatVisibility.FULL,
                true,
                List.of(SkinPart.values()),
                HandPreference.RIGHT_HAND
        ));

        byte[] data = new byte[] { 7, 118, 97, 110, 105, 108, 108, 97 };

        networkClient.sendPacket(
                new ClientPluginMessagePacket("minecraft:brand", data)
        );
    }
}
