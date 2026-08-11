package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.component.Component;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class InMemoryEntityRegistry implements InternalEntityRegistry {
    private final Set<Integer> activeEntities = new HashSet<>();
    private final Set<Integer> activeEntitiesView = Collections.unmodifiableSet(activeEntities);
    private final Map<Class<? extends Component>, Map<Integer, Component>> componentPools = new IdentityHashMap<>();

    @Override
    public void addEntity(int entityId) {
        activeEntities.add(entityId);
    }

    @Override
    public void removeEntity(int entityId) {
        if (!activeEntities.remove(entityId)) {
            return;
        }

        for (Map<Integer, Component> pool : componentPools.values()) {
            pool.remove(entityId);
        }
    }

    @Override
    public <T extends Component> void addComponent(int entityId, Class<T> componentType, T component) {
        Map<Integer, Component> pool = componentPools.computeIfAbsent(
                componentType,
                k -> new HashMap<>()
        );

        pool.put(entityId, component);
    }

    @Override
    public <T extends Component> T getComponent(int entityId, Class<T> componentType) {

        @SuppressWarnings("unchecked")
        Map<Integer, T> pool = (Map<Integer, T>) componentPools.get(componentType);

        return pool != null ? pool.get(entityId) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Component, M extends T> M getMutableComponent(int entityId, Class<T> componentType) {
        return (M) getComponent(entityId, componentType);
    }

    @Override
    public boolean has(int entityId, Class<? extends Component> componentType) {
        return getComponent(entityId, componentType) != null;
    }

    @Override
    public boolean has(int entityId, Class<? extends Component>[] componentTypes) {
        for (Class<? extends Component> componentType : componentTypes) {
            if (!has(entityId, componentType)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Set<Integer> getActiveEntities() {
        return activeEntitiesView;
    }
}
