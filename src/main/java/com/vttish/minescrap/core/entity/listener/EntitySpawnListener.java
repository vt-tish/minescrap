package com.vttish.minescrap.core.entity.listener;

@FunctionalInterface
public interface EntitySpawnListener extends EntityListener {
    void onSpawn(int entityId);
}
