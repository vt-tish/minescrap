package com.vttish.minescrap.api.entity;

import com.vttish.minescrap.api.entity.component.Component;

import java.util.Collection;

public interface EntityRegistry {
    <T extends Component> T getComponent(int entityId, Class<T> componentType);
    boolean has(int entityId, Class<? extends Component> componentType);
    boolean has(int entityId, Class<? extends Component>[] componentType);

    Collection<Integer> getActiveEntities();
}
