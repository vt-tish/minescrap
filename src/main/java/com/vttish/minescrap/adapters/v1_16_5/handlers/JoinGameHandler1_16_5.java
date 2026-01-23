package com.vttish.minescrap.adapters.v1_16_5.handlers;

import com.github.steveice10.mc.protocol.data.game.entity.player.HandPreference;
import com.github.steveice10.mc.protocol.data.game.setting.ChatVisibility;
import com.github.steveice10.mc.protocol.data.game.setting.SkinPart;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientPluginMessagePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.vttish.minescrap.core.network.NetworkClient;
import com.vttish.minescrap.core.service.ConnectionService;

import java.util.List;

public class JoinGameHandler1_16_5 {
    private final NetworkClient networkClient;
    private final ConnectionService connectionService;

    public JoinGameHandler1_16_5(NetworkClient networkClient, ConnectionService connectionService) {
        this.networkClient = networkClient;
        this.connectionService = connectionService;
    }

    public void handle(ServerJoinGamePacket packet) {
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
