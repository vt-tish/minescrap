package com.vttish.minescrap.core;

import com.vttish.minescrap.api.MinecraftBot;
import com.vttish.minescrap.api.entity.Player;
import com.vttish.minescrap.api.event.Listenable;
import com.vttish.minescrap.api.event.Subscription;
import com.vttish.minescrap.core.event.EventRegister;
import com.vttish.minescrap.core.network.ActionSender;
import com.vttish.minescrap.core.network.NetworkClient;
import com.vttish.minescrap.core.service.CoreServices;

public class MinecraftBotImpl implements MinecraftBot {
    private final String username;
    private final EventRegister eventRegister = new EventRegister();

    private final NetworkClient networkClient;
    private final ActionSender actionSender;
    private final CoreServices coreServices;

    public MinecraftBotImpl(
            String username,
            NetworkClient networkClient,
            ActionSender actionSender
    ) {
        this.username = username;

        this.networkClient = networkClient;
        this.actionSender = actionSender;
        this.coreServices = new CoreServices(this);
    }

    @Override
    public void connect() {
        coreServices.connectionService.connect();
    }

    @Override
    public void enableReconnect(int delayMs) {
        coreServices.connectionService.enableReconnect(delayMs);
    }

    @Override
    public void enableReconnect() {
        coreServices.connectionService.enableReconnect(5000);
    }

    @Override
    public void disableReconnect() {
        coreServices.connectionService.disableReconnect();
    }

    @Override
    public boolean isConnected() {
        return networkClient.isConnected();
    }

    @Override
    public void disconnect() {
        coreServices.connectionService.disconnect();
    }

    @Override
    public void disconnect(String reason) {
        coreServices.connectionService.disconnect(reason);
    }

    @Override
    public void chat(String message) {
        coreServices.chatService.chat(message);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Player getPlayer() {
        return coreServices.playerService.getPlayer();
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

    public NetworkClient getNetworkClient() {
        return networkClient;
    }

    public ActionSender getActionSender() {
        return actionSender;
    }

    public CoreServices getCoreServices() {
        return coreServices;
    }
}
