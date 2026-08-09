package com.vttish.minescrap.core.entity.capability;

import com.vttish.minescrap.api.entity.capability.Capability;
import com.vttish.minescrap.api.entity.capability.CapabilityFactory;
import com.vttish.minescrap.api.entity.capability.CapabilityProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.IdentityHashMap;
import java.util.Map;

@Singleton
public class DefaultCapabilityFactory implements CapabilityFactory {
    private final Map<Class<? extends Capability>, CapabilityProvider<?>> providers;

    @Inject
    public DefaultCapabilityFactory(Map<Class<? extends Capability>, CapabilityProvider<?>> providers) {
        this.providers = new IdentityHashMap<>(providers);
    }

    @Override
    public <T extends Capability> void register(Class<T> capabilityType, CapabilityProvider<T> provider) {
        providers.put(capabilityType, provider);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Capability> CapabilityProvider<T> getProvider(Class<T> capability) {
        return (CapabilityProvider<T>) providers.get(capability);
    }
}
