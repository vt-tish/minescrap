package com.vttish.minescrap.api.event;

public interface Events {
    @FunctionalInterface
    interface Disconnect extends Listenable {
        void onDisconnect(String reason);
    }

    @FunctionalInterface
    interface Chat extends Listenable {
        void onChat(String message);
    }

    @FunctionalInterface
    interface Spawn extends Listenable {
        void onSpawn();
    }
}
