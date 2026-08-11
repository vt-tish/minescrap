package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.EntityManager;
import com.vttish.minescrap.api.entity.Entity;
import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.core.entity.listener.EntityListenerDispatcher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class TrackedEntityManager implements EntityManager {
    private final EntityFactory entityFactory;
    private final Map<Integer, Entity> entities = new HashMap<>();
    private final Collection<Entity> entitiesView = Collections.unmodifiableCollection(entities.values());

    @Inject
    public TrackedEntityManager(
            EntityFactory entityFactory,
            EntityListenerDispatcher listenerDispatcher
    ) {
        this.entityFactory = entityFactory;

        listenerDispatcher.addSpawnListener(this::handleSpawn);
        listenerDispatcher.addDespawnListener(this::handleDespawn);
    }

    @Override
    public Collection<Entity> getEntities() {
        return entitiesView;
    }

    private void handleSpawn(int entityId) {
        entities.put(entityId, entityFactory.create(entityId));
    }

    private void handleDespawn(int entityId) {
        entities.remove(entityId);
    }
}

