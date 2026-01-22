package com.vttish.minescrap.core.handlers;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.MinecraftBotImpl;

public class ConnectionHandler {
    private final MinecraftBotImpl minecraftBot;

    public ConnectionHandler(MinecraftBotImpl minecraftBot) {
        this.minecraftBot = minecraftBot;
    }

    public void handleDisconnect(String reason) {
        minecraftBot.getEventRegister().notifyListeners(Events.Disconnect.class, listener -> {
            listener.onDisconnect(reason);
        });
    }

    public void handleJoinGame(int entityId) {
        minecraftBot.getPlayerImpl().setEntityId(entityId);
        minecraftBot.getEventRegister().notifyListeners(Events.Join.class, Events.Join::onJoin);
    }
}
