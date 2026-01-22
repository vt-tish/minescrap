package com.vttish.minescrap.adapters.v1_16_5.network;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerRotationPacket;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.network.ActionSender;

public class ActionSender1_16_5 implements ActionSender {
    private final MinecraftBotImpl minecraftBot;

    public ActionSender1_16_5(MinecraftBotImpl minecraftBot) {
        this.minecraftBot = minecraftBot;
    }

    @Override
    public void sendLocation(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        minecraftBot.getNetworkClient().sendPacket(new ClientPlayerPositionRotationPacket(
                onGround,
                x, y, z,
                yaw, pitch
        ));
    }

    @Override
    public void sendPosition(double x, double y, double z, boolean onGround) {
        minecraftBot.getNetworkClient().sendPacket(new ClientPlayerPositionPacket(
                onGround,
                x, y, z
        ));
    }

    @Override
    public void sendRotation(float yaw, float pitch, boolean onGround) {
        minecraftBot.getNetworkClient().sendPacket(new ClientPlayerRotationPacket(
                onGround,
                yaw, pitch
        ));
    }

    @Override
    public void sendOnGround(boolean onGround) {
        minecraftBot.getNetworkClient().sendPacket(new ClientPlayerMovementPacket(onGround));
    }

    @Override
    public void sendChat(String message) {
        minecraftBot.getNetworkClient().sendPacket(new ClientChatPacket(message));
    }
}
