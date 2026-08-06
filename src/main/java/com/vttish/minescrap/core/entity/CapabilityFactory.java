package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.Capability;

import javax.inject.Inject;
import java.util.IdentityHashMap;
import java.util.Map;

public class CapabilityFactory {
    private final Map<Class<?>, CapabilityProvider<?>> providers;

    @Inject
    public CapabilityFactory(Map<Class<?>, CapabilityProvider<?>> providers) {
        this.providers = new IdentityHashMap<>(providers);
    }

    @SuppressWarnings("unchecked")
    public <T extends Capability> CapabilityProvider<T> getProvider(Class<T> capability) {
        return (CapabilityProvider<T>) providers.get(capability);
    }
}
