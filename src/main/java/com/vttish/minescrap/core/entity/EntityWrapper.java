package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.Capability;
import com.vttish.minescrap.api.entity.Entity;

import java.util.Optional;

public class EntityWrapper implements Entity {
    private final int id;
    private final EntityRegistry entityRegistry;
    private final CapabilityFactory capabilityFactory;

    public EntityWrapper(int id, EntityRegistry entityRegistry, CapabilityFactory capabilityFactory) {
        this.id = id;
        this.entityRegistry = entityRegistry;
        this.capabilityFactory = capabilityFactory;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public <T extends Capability> boolean has(Class<T> capabilityType) {
        CapabilityProvider<T> provider = capabilityFactory.getProvider(capabilityType);
        return provider != null && entityRegistry.has(id, provider.getRequiredComponents());
    }

    @Override
    public <T extends Capability> T as(Class<T> capabilityType) {
        CapabilityProvider<T> provider = capabilityFactory.getProvider(capabilityType);

        if (provider == null) {
            throw new IllegalArgumentException("No provider registered for capability: " +
                    capabilityType.getSimpleName());
        }

        if (!entityRegistry.has(id, provider.getRequiredComponents())) {
            throw new IllegalStateException("Entity " + id + " does not have the required components for capability: "
                    + capabilityType.getSimpleName());
        }

        return provider.create(id, entityRegistry);
    }

    @Override
    public <T extends Capability> Optional<T> asOpt(Class<T> capabilityType) {
        CapabilityProvider<T> provider = capabilityFactory.getProvider(capabilityType);

        if (provider != null && entityRegistry.has(id, provider.getRequiredComponents())) {
            return Optional.of(provider.create(id, entityRegistry));
        }

        return Optional.empty();
    }
}
