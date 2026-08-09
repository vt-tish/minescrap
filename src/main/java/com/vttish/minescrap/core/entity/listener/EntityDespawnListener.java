package com.vttish.minescrap.core.entity.listener;

@FunctionalInterface
public interface EntityDespawnListener extends Listener {
    void onDespawn(int entityId);
}
