package com.vttish.minescrap.core.service;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.event.EventRegister;

import java.util.UUID;

public class ConnectionService {
    private final EventRegister eventRegister;
    private final PlayerService playerService;

    public ConnectionService(EventRegister eventRegister, PlayerService playerService) {
        this.eventRegister = eventRegister;
        this.playerService = playerService;
    }

    public void handleLogin(String username, UUID uuid) {
        playerService.createPlayer(username, uuid);
    }

    public void handleDisconnect(String reason) {
        eventRegister.notifyListeners(Events.Disconnect.class, listener -> {
            listener.onDisconnect(reason);
        });
    }

    public void handleJoinGame(int entityId) {
        playerService.setPlayerEntityId(entityId);
        eventRegister.notifyListeners(Events.Join.class, Events.Join::onJoin);
    }
}
