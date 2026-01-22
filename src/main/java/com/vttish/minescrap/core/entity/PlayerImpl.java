package com.vttish.minescrap.core.entity;

import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.api.entity.EntityType;
import com.vttish.minescrap.api.entity.Player;

import java.util.UUID;

public class PlayerImpl extends BaseEntity implements Player {
    private final String username;
    private volatile int health;
    private volatile int food;

    public PlayerImpl(
            String username,
            int health,
            int food,
            Location location,
            boolean onGround,
            int entityId,
            UUID uuid
    ) {
        super(location, EntityType.PLAYER, entityId, uuid, onGround);
        this.username = username;
        this.health = health;
        this.food = food;
    }

    public PlayerImpl(
            String username,
            int health,
            int food,
            int entityId,
            UUID uuid
    ) {
        this(username, health, food, new Location(), false, entityId, uuid);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getFood() {
        return food;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setFood(int food) {
        this.food = food;
    }
}
