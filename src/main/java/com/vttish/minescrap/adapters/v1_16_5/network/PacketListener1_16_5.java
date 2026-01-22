package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerRespawnPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.login.server.LoginSuccessPacket;
import com.github.steveice10.packetlib.packet.Packet;
import com.vttish.minescrap.adapters.v1_16_5.handlers.JoinGameHandler1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.handlers.MovementHandler1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.util.ComponentHelper;
import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.entity.PlayerImpl;
import com.vttish.minescrap.core.handlers.ChatHandler;
import com.vttish.minescrap.core.handlers.ConnectionHandler;
import com.vttish.minescrap.core.handlers.PlayerHandler;
import com.vttish.minescrap.core.handlers.RespawnHandler;
import com.vttish.minescrap.core.network.NetworkClient;
import org.checkerframework.checker.units.qual.C;

import java.util.UUID;

public class PacketListener1_16_5 implements NetworkClient.PacketListener {
    private final MinecraftBotImpl minecraftBot;

    private final MovementHandler1_16_5 movementHandler1_16_5;
    private final JoinGameHandler1_16_5 joinGameHandler1_16_5;

    private final PlayerHandler playerHandler;
    private final ConnectionHandler connectionHandler;
    private final ChatHandler chatHandler;
    private final RespawnHandler respawnHandler;

    public PacketListener1_16_5(MinecraftBotImpl minecraftBot) {
        this.minecraftBot = minecraftBot;
        this.playerHandler = new PlayerHandler(minecraftBot);
        this.movementHandler1_16_5 = new MovementHandler1_16_5(minecraftBot, playerHandler);
        this.connectionHandler = new ConnectionHandler(minecraftBot);
        this.joinGameHandler1_16_5 = new JoinGameHandler1_16_5(minecraftBot, connectionHandler);
        this.chatHandler = new ChatHandler(minecraftBot);
        this.respawnHandler = new RespawnHandler(minecraftBot);
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (!(packet instanceof Packet)) {
            throw new IllegalArgumentException("[MCProtocolNetworkClient]: Object should be an instance of Packet");
        }

        if (packet instanceof ServerConfirmTransactionPacket p) {
            minecraftBot.getNetworkClient().sendPacket(new ClientConfirmTransactionPacket(
                    p.getWindowId(),
                    p.getActionId(),
                    p.isAccepted())
            );
        } else if (packet instanceof ServerPlayerPositionRotationPacket p) {
            movementHandler1_16_5.handle(p);
        } else if (packet instanceof ServerChatPacket p) {
            chatHandler.handle(ComponentHelper.toPlainText(p.getMessage()));
        }
        else if (packet instanceof ServerRespawnPacket) {
            respawnHandler.handle();
        } else if (packet instanceof ServerJoinGamePacket p) {
            joinGameHandler1_16_5.handle(p);
        } else if (packet instanceof LoginSuccessPacket p) {
            minecraftBot.setPlayer(new PlayerImpl(
                    p.getProfile().getName(),
                    20,
                    20,
                    new Location(),
                    false,
                    -1,
                    p.getProfile().getId()
            ));
        }
    }

    @Override
    public void onDisconnected(String reason) {
        connectionHandler.handleDisconnect(reason);
    }
}
