package com.vttish.minescrap.factory;

import com.vttish.minescrap.adapters.v1_16_5.MinecraftBot1_16_5;
import com.vttish.minescrap.api.MinecraftBot;

public class MinecraftBotFactory {
    public static MinecraftBot create(MinecraftBotVersion version, String username, String host, int port) {
        return switch (version) {
            case v1_16_5 -> MinecraftBot1_16_5.create(username, host, port);
            case v1_8 -> throw new UnsupportedOperationException("[MinecraftBotFactory]: Unsupported version");
        };
    }

    public static MinecraftBot create(MinecraftBotVersion version, String username, String host) {
        return create(version, username, host, 25565);
    }
}
