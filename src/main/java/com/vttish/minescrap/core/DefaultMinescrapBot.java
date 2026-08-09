package com.vttish.minescrap.core;

import com.vttish.minescrap.api.MinescrapBot;
import com.vttish.minescrap.api.MinescrapBotConfig;
import com.vttish.minescrap.api.entity.EntityManager;
import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.api.entity.capability.CapabilityFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DefaultMinescrapBot implements MinescrapBot {
    private final MinescrapBotConfig config;
    private final EntityRegistry entityRegistry;
    private final CapabilityFactory capabilityFactory;
    private final EntityManager entityManager;

    @Inject
    public DefaultMinescrapBot(
            MinescrapBotConfig config,
            EntityRegistry entityRegistry,
            CapabilityFactory capabilityFactory,
            EntityManager entityManager
    ) {
        this.config = config;
        this.entityRegistry = entityRegistry;
        this.capabilityFactory = capabilityFactory;
        this.entityManager = entityManager;
    }

    @Override
    public void connect() {
        // TODO: Implement network layer to provide this method
    }

    @Override
    public void disconnect() {
        // TODO: Implement network layer to provide this method
    }

    @Override
    public boolean isConnected() {
        // TODO: Implement network layer to provide this method
        return false;
    }

    @Override
    public EntityRegistry entityRegistry() {
        return entityRegistry;
    }

    @Override
    public CapabilityFactory capabilityFactory() {
        return capabilityFactory;
    }

    @Override
    public EntityManager entityManager() {
        return entityManager;
    }

    @Override
    public MinescrapBotConfig config() {
        return config;
    }
}
