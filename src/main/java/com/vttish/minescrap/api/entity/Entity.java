package com.vttish.minescrap.api.entity;

import com.vttish.minescrap.api.common.Identifier;
import com.vttish.minescrap.api.common.math.Vector3d;
import com.vttish.minescrap.api.entity.capability.Capability;

import java.util.Optional;

public interface Entity {
    int getId();
    Identifier getType();

    Vector3d getPosition();
    Vector3d getVelocity();

    <T extends Capability> boolean has(Class<T> capability);

    <T extends Capability> T as(Class<T> capability);
    <T extends Capability> Optional<T> asOpt(Class<T> capability);
}
