package com.vttish.minescrap.core;

import com.vttish.minescrap.api.MinecraftBot;
import com.vttish.minescrap.api.entity.Player;
import com.vttish.minescrap.api.event.Listenable;
import com.vttish.minescrap.api.event.Subscription;
import com.vttish.minescrap.core.entity.PlayerImpl;
import com.vttish.minescrap.core.event.EventRegister;
import com.vttish.minescrap.core.network.ActionSender;
import com.vttish.minescrap.core.network.NetworkClient;

import java.util.function.Function;

public class MinecraftBotImpl implements MinecraftBot {
    private final String username;
    private PlayerImpl player;
    private final EventRegister eventRegister = new EventRegister();

    private final NetworkClient networkClient;
    private final ActionSender actionSender;

    public MinecraftBotImpl(
            String username,
            Function<MinecraftBotImpl, NetworkClient> networkClientFactory,
            Function<MinecraftBotImpl, ActionSender> actionSenderFactory
    ) {
        this.username = username;
        this.networkClient = networkClientFactory.apply(this);
        this.actionSender = actionSenderFactory.apply(this);
    }

    @Override
    public void connect() {
        networkClient.connect();
    }

    @Override
    public boolean isConnected() {
        return networkClient.isConnected();
    }

    @Override
    public void disconnect() {
        networkClient.disconnect();
    }

    @Override
    public void disconnect(String reason) {
        networkClient.disconnect(reason);
    }

    @Override
    public void chat(String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("[MinecraftBotImpl]: Message is empty");
        }

        if (!networkClient.isConnected()) {
            throw new IllegalArgumentException("[MinecraftBotImpl]: Client is not connected");
        }

        actionSender.sendChat(message);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public <T extends Listenable> Subscription once(Class<T> event, T listener) {
        return eventRegister.addOnceListener(event, listener);
    }

    @Override
    public <T extends Listenable> Subscription on(Class<T> event, T listener) {
        return eventRegister.addListener(event, listener);
    }

    public EventRegister getEventRegister() {
        return eventRegister;
    }

    public PlayerImpl getPlayerImpl() {
        return player;
    }

    public void setPlayer(PlayerImpl player) {
        this.player = player;
    }

    public NetworkClient getNetworkClient() {
        return networkClient;
    }
}
