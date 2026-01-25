package com.vttish.minescrap.api;

import com.vttish.minescrap.api.entity.Player;
import com.vttish.minescrap.api.event.Listenable;
import com.vttish.minescrap.api.event.Subscription;

public interface MinecraftBot {
    void connect();

    void enableReconnect(int delayMs);
    void enableReconnect();
    void disableReconnect();

    boolean isConnected();

    void disconnect();
    void disconnect(String reason);

    void chat(String message);

    String getUsername();
    Player getPlayer();

    <T extends Listenable> Subscription on(Class<T> event, T listener);
    <T extends Listenable> Subscription once(Class<T> event, T listener);
}
