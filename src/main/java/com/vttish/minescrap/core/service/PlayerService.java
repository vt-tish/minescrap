package com.vttish.minescrap.core.service;

import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.api.entity.Player;
import com.vttish.minescrap.core.entity.PlayerImpl;

import java.util.UUID;

public class PlayerService {
    private PlayerImpl player;

    void createPlayer(String username, UUID uuid) {
        player = new PlayerImpl(
                username,
                20,
                20,
                new Location(),
                false,
                -1,
                uuid
        );
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerEntityId(int entityId) {
        player.setEntityId(entityId);
    }

    public void handlePositionRotation(Location location) {
        player.setLocation(location);
        player.setOnGround(false);
    }

}
