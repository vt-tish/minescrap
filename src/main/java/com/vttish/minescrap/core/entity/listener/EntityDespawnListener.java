package com.vttish.minescrap.core.entity.listener;

@FunctionalInterface
public interface EntityDespawnListener extends EntityListener {
    void onDespawn(int entityId);
}
