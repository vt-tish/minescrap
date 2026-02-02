package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.core.entity.PlayerImpl;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.EntityService;
import com.vttish.minescrap.core.service.TabListService;

public class EntityListener1_16_5 implements PacketListener {
    private final EntityService entityService;
    private final TabListService tabListService;

    public EntityListener1_16_5(EntityService entityService, TabListService tabListService) {
        this.entityService = entityService;
        this.tabListService = tabListService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerSpawnPlayerPacket p) {
            entityService.handleSpawnEntity(new PlayerImpl(
                    tabListService.getName(p.getUuid()),
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
