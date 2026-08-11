package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.Entity;
import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.api.entity.capability.CapabilityFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class EntityFactory {
    private final EntityRegistry entityRegistry;
    private final CapabilityFactory capabilityFactory;

    @Inject
    public EntityFactory(EntityRegistry entityRegistry, CapabilityFactory capabilityFactory) {
        this.entityRegistry = entityRegistry;
        this.capabilityFactory = capabilityFactory;
    }

    public Entity create(int entityId) {
        return new DefaultEntity(entityId, entityRegistry, capabilityFactory);
    }
}
