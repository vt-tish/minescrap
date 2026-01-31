package com.vttish.minescrap.core.service;

import com.vttish.minescrap.api.entity.Entity;
import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.event.EventRegister;

public class EntityService {
    private final EventRegister eventRegister;

    public EntityService(EventRegister eventRegister) {
        this.eventRegister = eventRegister;
    }

    public void handleSpawnEntity(Entity entity) {
        eventRegister.notifyListeners(Events.EntitySpawn.class, listener -> {
            listener.onEntitySpawn(entity);
        });
    }

}
