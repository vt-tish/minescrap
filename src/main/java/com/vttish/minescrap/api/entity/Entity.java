package com.vttish.minescrap.api.entity;

import java.util.Optional;

public interface Entity {
    int getId();

    <T extends Capability> boolean has(Class<T> capability);

    <T extends Capability> T as(Class<T> capability);
    <T extends Capability> Optional<T> asOpt(Class<T> capability);
}
