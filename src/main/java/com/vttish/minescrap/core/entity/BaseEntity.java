package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.api.entity.Entity;
import com.vttish.minescrap.api.entity.EntityType;

import java.util.UUID;

public abstract class BaseEntity implements Entity {
    private volatile Location location;
    private final EntityType entityType;
    private final UUID uuid;
    private volatile int entityId;
    private volatile boolean onGround;

    public BaseEntity(Location location, EntityType entityType, int entityId, UUID uuid, boolean onGround) {
        this.location = location;
        this.entityType = entityType;
        this.entityId = entityId;
        this.uuid = uuid;
        this.onGround = onGround;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
