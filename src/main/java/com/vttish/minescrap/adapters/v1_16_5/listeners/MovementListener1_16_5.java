package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.data.game.entity.player.PositionElement;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientTeleportConfirmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.core.network.NetworkClient;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.PlayerService;

public class MovementListener1_16_5 implements PacketListener {
    private final NetworkClient networkClient;
    private final PlayerService playerService;

    public MovementListener1_16_5(NetworkClient networkClient, PlayerService playerService) {
        this.networkClient = networkClient;
        this.playerService = playerService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerPlayerPositionRotationPacket p) {
            handlePositionRotation(p);
        }
    }

    private void handlePositionRotation(ServerPlayerPositionRotationPacket packet) {
        double x = packet.getX();
        double y = packet.getY();
        double z = packet.getZ();
        float pitch = packet.getPitch();
        float yaw = packet.getYaw();

        Location currentLocation = playerService.getPlayer().getLocation();

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

        networkClient.sendPacket(new ClientTeleportConfirmPacket(packet.getTeleportId()));
        networkClient.sendPacket(new ClientPlayerPositionRotationPacket(false, x, y, z, yaw, pitch));

        playerService.handlePositionRotation(currentLocation);
    }
}
