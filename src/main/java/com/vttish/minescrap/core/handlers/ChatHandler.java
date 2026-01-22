package com.vttish.minescrap.core.handlers;

import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.event.EventRegister;

public class ChatHandler {
    private final MinecraftBotImpl minecraftBot;

    public ChatHandler(MinecraftBotImpl minecraftBot) {
        this.minecraftBot = minecraftBot;
    }

    public void handle(String message) {
        minecraftBot.getEventRegister().notifyListeners(Events.Chat.class, listener -> {
            listener.onChat(message);
        });
    }
}
