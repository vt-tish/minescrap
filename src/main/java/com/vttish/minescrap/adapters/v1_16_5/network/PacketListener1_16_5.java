package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerRespawnPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.login.server.LoginSuccessPacket;
import com.github.steveice10.packetlib.packet.Packet;
import com.vttish.minescrap.adapters.v1_16_5.handlers.Handlers1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.util.ComponentHelper;
import com.vttish.minescrap.core.service.CoreServices;
import com.vttish.minescrap.core.network.NetworkClient;

public class PacketListener1_16_5 implements NetworkClient.PacketListener {
    private final CoreServices coreServices;
    private final NetworkClient networkClient;
    private final Handlers1_16_5 handlers1_16_5;

    public PacketListener1_16_5(
            NetworkClient networkClient,
            CoreServices coreServices,
            Handlers1_16_5 handlers1_16_5
    ) {
        this.coreServices = coreServices;
        this.networkClient = networkClient;
        this.handlers1_16_5 = handlers1_16_5;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (!(packet instanceof Packet)) {
            throw new IllegalArgumentException("[MCProtocolNetworkClient]: Object should be an instance of Packet");
        }

        if (packet instanceof ServerConfirmTransactionPacket p) {
            networkClient.sendPacket(new ClientConfirmTransactionPacket(
                    p.getWindowId(),
                    p.getActionId(),
                    p.isAccepted())
            );
        } else if (packet instanceof ServerPlayerPositionRotationPacket p) {
            handlers1_16_5.movementHandler1_16_5.handle(p);
        } else if (packet instanceof ServerChatPacket p) {
            coreServices.chatService.handleChat(ComponentHelper.toPlainText(p.getMessage()));
        }
        else if (packet instanceof ServerRespawnPacket) {
            coreServices.spawnService.handleRespawn();
        } else if (packet instanceof ServerJoinGamePacket p) {
            handlers1_16_5.joinGameHandler1_16_5.handle(p);
        } else if (packet instanceof LoginSuccessPacket p) {
            coreServices.connectionService.handleLogin(p.getProfile().getName(), p.getProfile().getId());
        }
    }

    @Override
    public void onDisconnected(String reason) {
        coreServices.connectionService.handleDisconnect(reason);
    }
}
