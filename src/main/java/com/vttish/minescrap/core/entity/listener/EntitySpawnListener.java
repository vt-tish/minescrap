package com.vttish.minescrap.core.entity.listener;

@FunctionalInterface
public interface EntitySpawnListener extends Listener {
    void onSpawn(int entityId);
}
