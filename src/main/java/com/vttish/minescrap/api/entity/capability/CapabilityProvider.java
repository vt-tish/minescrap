package com.vttish.minescrap.api.entity.capability;

import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.api.entity.component.Component;

public interface CapabilityProvider<T extends Capability> {
    Class<? extends Capability> getCapabilityType();
    Class<? extends Component>[] getRequiredComponents();
    T create(int entityId, EntityRegistry entityRegistry);
}
