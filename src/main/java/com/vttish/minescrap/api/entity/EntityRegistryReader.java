package com.vttish.minescrap.api.entity;

import com.vttish.minescrap.core.entity.component.Component;

import java.util.Set;

public interface EntityRegistryReader {
    <T extends Component> T getComponent(int entityId, Class<T> componentType);
    boolean has(int entityId, Class<? extends Component> componentType);
    boolean has(int entityId, Class<? extends Component>[] componentType);

    Set<Integer> getActiveEntities();
}
