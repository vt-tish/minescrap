package com.vttish.minescrap.api.entity.capability;

public interface CapabilityFactory {
    public <T extends Capability> void register(Class<T> capabilityType, CapabilityProvider<T> provider);
    public <T extends Capability> CapabilityProvider<T> getProvider(Class<T> capability);
}
