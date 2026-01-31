package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.core.entity.PlayerImpl;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.EntityService;

public class EntityListener1_16_5 implements PacketListener {
    private final EntityService entityService;

    public EntityListener1_16_5(EntityService entityService) {
        this.entityService = entityService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerSpawnPlayerPacket p) {
            entityService.handleSpawnEntity(new PlayerImpl(
                    "Test",
                    20,
                    20,
                    new Location(p.getX(), p.getY(), p.getZ(), p.getPitch(), p.getYaw()),
                    false,
                    p.getEntityId(),
                    p.getUuid()
            ));
        }
    }
}
