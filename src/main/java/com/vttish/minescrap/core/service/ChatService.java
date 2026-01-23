package com.vttish.minescrap.core.service;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.event.EventRegister;
import com.vttish.minescrap.core.network.ActionSender;
import com.vttish.minescrap.core.network.NetworkClient;

public class ChatService {
    private final EventRegister eventRegister;
    private final ActionSender actionSender;
    private final NetworkClient networkClient;

    public ChatService(EventRegister eventRegister, ActionSender actionSender, NetworkClient networkClient) {
        this.eventRegister = eventRegister;
        this.actionSender = actionSender;
        this.networkClient = networkClient;
    }

    public void handleChat(String message) {
        eventRegister.notifyListeners(Events.Chat.class, listener -> {
            listener.onChat(message);
        });
    }

    public void chat(String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("[ChatService]: Message is empty");
        }

        if (!networkClient.isConnected()) {
            throw new IllegalArgumentException("[ChatService]: Client is not connected");
        }

        actionSender.sendChat(message);
    }
}
