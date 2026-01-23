package com.vttish.minescrap.adapters.v1_16_5;

import com.vttish.minescrap.adapters.v1_16_5.handlers.Handlers1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.network.ActionSender1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.network.MCProtocolNetworkClient1_16_5;
import com.vttish.minescrap.adapters.v1_16_5.network.PacketListener1_16_5;
import com.vttish.minescrap.api.MinecraftBot;
import com.vttish.minescrap.core.MinecraftBotImpl;
import com.vttish.minescrap.core.network.ActionSender;
import com.vttish.minescrap.core.network.NetworkClient;

public class MinecraftBot1_16_5 {
    public static MinecraftBot create(String username, String host, int port) {
        NetworkClient networkClient = new MCProtocolNetworkClient1_16_5(username, host, port);
        ActionSender actionSender = new ActionSender1_16_5(networkClient);
        MinecraftBotImpl minecraftBot = new MinecraftBotImpl(
                username,
                networkClient,
                actionSender
        );

        Handlers1_16_5 handlers1_16_5 = new Handlers1_16_5(networkClient, minecraftBot.getCoreServices());
        networkClient.setPacketListener(new PacketListener1_16_5(
                networkClient,
                minecraftBot.getCoreServices(),
                handlers1_16_5
        ));

        return minecraftBot;
    }
}
