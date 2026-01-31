package com.vttish.minescrap.core.service;

import com.vttish.minescrap.core.MinecraftBotImpl;

public class CoreServices {
    public final ConnectionService connectionService;
    public final PlayerService playerService;
    public final ChatService chatService;
    public final SpawnService spawnService;
    public final EntityService entityService;

    public CoreServices(MinecraftBotImpl minecraftBot) {
        this.playerService = new PlayerService();
        this.connectionService = new ConnectionService(
                minecraftBot.getNetworkClient(),
                minecraftBot.getEventRegister(),
                playerService
        );
        this.chatService = new ChatService(
                minecraftBot.getEventRegister(),
                minecraftBot.getActionSender(),
                minecraftBot.getNetworkClient()
        );
        this.spawnService = new SpawnService(minecraftBot.getEventRegister());
        this.entityService = new EntityService(minecraftBot.getEventRegister());
    }
}
