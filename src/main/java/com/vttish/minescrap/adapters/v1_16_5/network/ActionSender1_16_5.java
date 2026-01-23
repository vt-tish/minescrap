package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerRotationPacket;
import com.vttish.minescrap.core.network.ActionSender;
import com.vttish.minescrap.core.network.NetworkClient;

public class ActionSender1_16_5 implements ActionSender {
    private final NetworkClient networkClient;

    public ActionSender1_16_5(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    @Override
    public void sendLocation(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        networkClient.sendPacket(new ClientPlayerPositionRotationPacket(
                onGround,
                x, y, z,
                yaw, pitch
        ));
    }

    @Override
    public void sendPosition(double x, double y, double z, boolean onGround) {
        networkClient.sendPacket(new ClientPlayerPositionPacket(
                onGround,
                x, y, z
        ));
    }

    @Override
    public void sendRotation(float yaw, float pitch, boolean onGround) {
        networkClient.sendPacket(new ClientPlayerRotationPacket(
                onGround,
                yaw, pitch
        ));
    }

    @Override
    public void sendOnGround(boolean onGround) {
        networkClient.sendPacket(new ClientPlayerMovementPacket(onGround));
    }

    @Override
    public void sendChat(String message) {
        networkClient.sendPacket(new ClientChatPacket(message));
    }
}
