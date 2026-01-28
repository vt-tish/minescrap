package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerRespawnPacket;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.SpawnService;

public class WorldListener1_16_5 implements PacketListener {
    private final SpawnService spawnService;

    public WorldListener1_16_5(SpawnService spawnService) {
        this.spawnService = spawnService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerRespawnPacket) {
            spawnService.handleRespawn();
        }
    }
}
