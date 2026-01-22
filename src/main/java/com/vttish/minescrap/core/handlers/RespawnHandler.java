package com.vttish.minescrap.core.handlers;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.event.EventRegister;

public class RespawnHandler {
    private final MinecraftBotImpl minecraftBot;

    public RespawnHandler(MinecraftBotImpl minecraftBot) {
        this.minecraftBot = minecraftBot;
    }

    public void handle() {
        minecraftBot.getEventRegister().notifyListeners(Events.Respawn.class, Events.Respawn::onRespawn);
    }
}
