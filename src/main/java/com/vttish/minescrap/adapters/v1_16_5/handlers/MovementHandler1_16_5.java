package com.vttish.minescrap.adapters.v1_16_5.handlers;

import com.github.steveice10.mc.protocol.data.game.entity.player.PositionElement;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientTeleportConfirmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.handlers.PlayerHandler;

public class MovementHandler1_16_5 {
    private final MinecraftBotImpl minecraftBot;
    private final PlayerHandler playerHandler;

    public MovementHandler1_16_5(MinecraftBotImpl minecraftBot, PlayerHandler playerHandler) {
        this.minecraftBot = minecraftBot;
        this.playerHandler = playerHandler;
    }

    public void handle(ServerPlayerPositionRotationPacket packet) {
        double x = packet.getX();
        double y = packet.getY();
        double z = packet.getZ();
        float pitch = packet.getPitch();
        float yaw = packet.getYaw();

        Location currentLocation = minecraftBot.getPlayer().getLocation();

        if (packet.getRelative().contains(PositionElement.X)) {
            x += currentLocation.x;
        }
        if (packet.getRelative().contains(PositionElement.Y)) {
            y += currentLocation.y;
        }
        if (packet.getRelative().contains(PositionElement.Z)) {
            z += currentLocation.z;
        }
        if (packet.getRelative().contains(PositionElement.PITCH)) {
            pitch += currentLocation.pitch;
        }
        if (packet.getRelative().contains(PositionElement.YAW)) {
            yaw += currentLocation.yaw;
        }

        currentLocation = new Location(x, y, z, pitch, yaw);

        minecraftBot.getNetworkClient().sendPacket(new ClientTeleportConfirmPacket(packet.getTeleportId()));
        minecraftBot.getNetworkClient().sendPacket(new ClientPlayerPositionRotationPacket(false, x, y, z, yaw, pitch));

        playerHandler.handlePositionRotation(currentLocation);
    }
}
