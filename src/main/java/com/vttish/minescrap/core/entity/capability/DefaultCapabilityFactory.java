package com.vttish.minescrap.core.entity.capability;

import com.vttish.minescrap.api.entity.capability.Capability;
import com.vttish.minescrap.api.entity.capability.CapabilityFactory;
import com.vttish.minescrap.api.entity.capability.CapabilityProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class DefaultCapabilityFactory implements CapabilityFactory {
    private final Map<Class<? extends Capability>, CapabilityProvider<?>> providers;

    @Inject
    @SuppressWarnings({ "rawtypes", "unchecked "})
    public DefaultCapabilityFactory(List<CapabilityProvider> providerList) {
        this.providers = providerList.stream()
                .collect(Collectors.toMap(
                        CapabilityProvider::getCapabilityType,
                        provider -> provider,
                        (existing, replacement) -> existing,
                        IdentityHashMap::new
                ));
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
