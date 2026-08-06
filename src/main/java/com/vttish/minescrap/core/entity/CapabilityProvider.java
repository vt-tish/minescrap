package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.entity.Capability;
import com.vttish.minescrap.api.entity.EntityRegistryReader;
import com.vttish.minescrap.core.entity.component.Component;

public interface CapabilityProvider<T extends Capability> {
    Class<? extends Component>[] getRequiredComponents();
    T create(int entityId, EntityRegistryReader entityRegistry);
}
