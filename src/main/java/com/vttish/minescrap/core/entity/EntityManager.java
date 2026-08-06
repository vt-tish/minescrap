package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.Capability;
import com.vttish.minescrap.api.entity.Entity;

import java.util.List;

public class EntityManager {
    private EntityRegistry entityRegistry;

    List<Entity> getEntities() {
        return List.of();
    }

    <T extends Capability> List<Entity> getWith(Class<T> capabilityType) {
        return List.of();
    }
}
