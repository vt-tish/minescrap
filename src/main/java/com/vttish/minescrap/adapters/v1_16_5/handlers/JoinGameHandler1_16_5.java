package com.vttish.minescrap.adapters.v1_16_5.handlers;

import com.github.steveice10.mc.protocol.data.game.entity.player.HandPreference;
import com.github.steveice10.mc.protocol.data.game.setting.ChatVisibility;
import com.github.steveice10.mc.protocol.data.game.setting.SkinPart;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientPluginMessagePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.handlers.ConnectionHandler;

import java.util.List;

public class JoinGameHandler1_16_5 {
    private final MinecraftBotImpl minecraftBot;
    private final ConnectionHandler connectionHandler;

    public JoinGameHandler1_16_5(MinecraftBotImpl minecraftBot, ConnectionHandler connectionHandler) {
        this.minecraftBot = minecraftBot;
        this.connectionHandler = connectionHandler;
    }

    public void handle(ServerJoinGamePacket packet) {
        connectionHandler.handleJoinGame(packet.getEntityId());

        minecraftBot.getNetworkClient().sendPacket(new ClientSettingsPacket(
                "en_us",
                2,
                ChatVisibility.FULL,
                true,
                List.of(SkinPart.values()),
                HandPreference.RIGHT_HAND
        ));

        byte[] data = new byte[] { 7, 118, 97, 110, 105, 108, 108, 97 };

        minecraftBot.getNetworkClient().sendPacket(
                new ClientPluginMessagePacket("minecraft:brand", data)
        );
    }
}
