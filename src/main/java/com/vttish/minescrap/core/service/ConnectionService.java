package com.vttish.minescrap.core.service;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.event.EventRegister;
import com.vttish.minescrap.core.network.NetworkClient;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionService {
    private final NetworkClient networkClient;
    private final EventRegister eventRegister;
    private final PlayerService playerService;

    private long reconnectDelayMs;
    private final AtomicBoolean isReconnectEnabled = new AtomicBoolean(false);
    private final AtomicBoolean isReconnecting = new AtomicBoolean(false);

    public ConnectionService(NetworkClient networkClient, EventRegister eventRegister, PlayerService playerService) {
        this.networkClient = networkClient;
        this.eventRegister = eventRegister;
        this.playerService = playerService;

        this.eventRegister.addListener(Events.Disconnect.class, reason -> {
            if (isReconnectEnabled.get() && !isReconnecting.get()) {
                reconnect(reconnectDelayMs);
            }
        });
    }

    public void handleLogin(String username, UUID uuid) {
        playerService.createPlayer(username, uuid);
    }

    public void handleDisconnect(String reason) {
        eventRegister.notifyListeners(Events.Disconnect.class, listener -> {
            listener.onDisconnect(reason);
        });
    }

    public void handleJoinGame(int playerEntityId) {
        playerService.setPlayerEntityId(playerEntityId);
        eventRegister.notifyListeners(Events.Join.class, Events.Join::onJoin);
    }

    public void connect() {
        try {
            connectAttempt();
        } catch (Exception ex) {
            if (isReconnectEnabled.get()) {
                reconnect(reconnectDelayMs);
            } else {
                throw ex;
            }
        }
    }

    public void disconnect() {
        disableReconnect();
        networkClient.disconnect();
    }

    public void disconnect(String reason) {
        disableReconnect();
        networkClient.disconnect(reason);
    }

    public void enableReconnect(long delayMs) {
        isReconnectEnabled.set(true);
        reconnectDelayMs = delayMs;
    }

    public void disableReconnect() {
        isReconnectEnabled.set(false);
    }

    private void connectAttempt() {
        networkClient.connect();

        if (networkClient.isConnected()) {
            eventRegister.notifyListeners(Events.Connect.class, Events.Connect::onConnect);
        }
    }

    private void reconnect(long delayMs) {
        if (!isReconnecting.compareAndSet(false, true)) {
            return;
        }

        new Thread(() -> {
            while (isReconnectEnabled.get() && !networkClient.isConnected()) {
                try {
                    Thread.sleep(delayMs);
                    connectAttempt();
                } catch (InterruptedException ex) {
                    break;
                } catch (Exception ex) {
                    System.out.println("[ConnectionService]: " + ex.getMessage());
                }
            }

            isReconnecting.set(false);
        }).start();
    }
}
