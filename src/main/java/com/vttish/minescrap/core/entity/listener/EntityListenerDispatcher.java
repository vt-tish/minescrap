package com.vttish.minescrap.core.entity.listener;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class EntityListenerDispatcher {
    private final List<EntitySpawnListener> spawnListeners = new ArrayList<>();
    private final List<EntityDespawnListener> despawnListeners = new ArrayList<>();

    @Inject
    protected EntityListenerDispatcher() {
    }

    public void addSpawnListener(EntitySpawnListener listener) {
        spawnListeners.add(listener);
    }

    public void addDespawnListener(EntityDespawnListener listener) {
        despawnListeners.add(listener);
    }

    public void dispatchSpawn(int entityId) {
        for (EntitySpawnListener listener : spawnListeners) {
            listener.onSpawn(entityId);
        }
    }

    public void dispatchDespawn(int entityId) {
        for (EntityDespawnListener listener : despawnListeners) {
            listener.onDespawn(entityId);
        }
    }
}
