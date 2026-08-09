package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.api.entity.component.Component;

public interface InternalEntityRegistry extends EntityRegistry {
    void addEntity(int entityId);
    void removeEntity(int entityId);

    <T extends Component> void addComponent(int entityId, Class<T> componentType, T component);
    <T extends Component, M extends T> M getMutableComponent(int entityId, Class<T> componentType);
}
