package com.vttish.minescrap.core.service;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.event.EventRegister;

public class SpawnService {
    private final EventRegister eventRegister;

    public SpawnService(EventRegister eventRegister) {
        this.eventRegister = eventRegister;
    }

    public void handleRespawn() {
        eventRegister.notifyListeners(Events.Respawn.class, Events.Respawn::onRespawn);
    }
}
