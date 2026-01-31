package com.vttish.minescrap.api.event;

import com.vttish.minescrap.api.entity.Entity;

public interface Events {
    @FunctionalInterface
    interface Disconnect extends Listenable {
        void onDisconnect(String reason);
    }

    @FunctionalInterface
    interface Connect extends Listenable {
        void onConnect();
    }

    @FunctionalInterface
    interface Chat extends Listenable {
        void onChat(String message);
    }

    @FunctionalInterface
    interface Join extends Listenable {
        void onJoin();
    }

    @FunctionalInterface
    interface Respawn extends Listenable {
        void onRespawn();
    }

    @FunctionalInterface
    interface EntitySpawn extends Listenable {
        void onEntitySpawn(Entity entity);
    }
}
