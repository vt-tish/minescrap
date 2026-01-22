package com.vttish.minescrap.api.entity;

import com.vttish.minescrap.api.data.Location;

import java.util.UUID;

public interface Entity {
    EntityType getEntityType();

    int getEntityId();
    UUID getUuid();

    Location getLocation();
    boolean isOnGround();
}
