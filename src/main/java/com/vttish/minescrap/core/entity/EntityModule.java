package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.EntityManager;
import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.api.entity.capability.CapabilityFactory;
import com.vttish.minescrap.core.entity.capability.DefaultCapabilityFactory;
import dagger.Binds;
import dagger.Module;

@Module
public interface EntityModule {

    @Binds
    InternalEntityRegistry bindInternalEntityRegistry(InMemoryEntityRegistry entityRegistry);

    @Binds
    EntityRegistry bindEntityRegistry(InternalEntityRegistry entityRegistry);

    @Binds
    CapabilityFactory bindCapabilityFactory(DefaultCapabilityFactory capabilityFactory);

    @Binds
    EntityManager bindEntityManager(TrackedEntityManager entityManager);
}
