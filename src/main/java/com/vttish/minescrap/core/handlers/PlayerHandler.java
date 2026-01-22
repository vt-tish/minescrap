package com.vttish.minescrap.core.handlers;

import com.vttish.minescrap.api.data.Location;
import com.vttish.minescrap.core.MinecraftBotImpl;

public class PlayerHandler {
    private final MinecraftBotImpl minecraftBot;

    public PlayerHandler(MinecraftBotImpl minecraftBot) {
        this.minecraftBot = minecraftBot;
    }

    public void handlePositionRotation(Location location) {
        minecraftBot.getPlayerImpl().setLocation(location);
        minecraftBot.getPlayerImpl().setOnGround(false);
    }
}
