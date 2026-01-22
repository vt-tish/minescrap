package com.vttish.minescrap.adapters.v1_16_5;

import com.vttish.minescrap.adapters.v1_16_5.network.ActionSender1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.network.MCProtocolNetworkClient1_16_5;
import com.vttish.minescrap.api.MinecraftBot;
import com.vttish.minescrap.core.MinecraftBotImpl;

public class MinecraftBot1_16_5 {
    public static MinecraftBot create(String username, String host, int port) {
        return new MinecraftBotImpl(
                username,
                minecraftBot -> new MCProtocolNetworkClient1_16_5(
                        username, host, port, minecraftBot
                ),
                ActionSender1_16_5::new
        );
    }
}
