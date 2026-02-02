package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.ConnectionService;
import com.vttish.minescrap.core.service.CoreServices;
import com.vttish.minescrap.core.network.NetworkClient;

import java.util.ArrayList;
import java.util.List;

public class PacketListener1_16_5 implements PacketListener {
    private final ConnectionService connectionService;
    private final List<PacketListener> listeners = new ArrayList<>();

    public PacketListener1_16_5(
            NetworkClient networkClient,
            CoreServices coreServices
    ) {
        this.connectionService = coreServices.connectionService;

        this.listeners.add(new ConnectionListener1_16_5(networkClient, coreServices.connectionService));
        this.listeners.add(new MovementListener1_16_5(networkClient, coreServices.playerService));
        this.listeners.add(new ChatListener1_16_5(coreServices.chatService));
        this.listeners.add(new WorldListener1_16_5(coreServices.spawnService));
        this.listeners.add(new EntityListener1_16_5(coreServices.entityService, coreServices.tabListService));
        this.listeners.add(new TabListListener1_16_5(coreServices.tabListService));
    }

    @Override
    public void onPackedReceived(Object packet) {
        for (PacketListener listener : listeners) {
            listener.onPackedReceived(packet);
        }
    }

    @Override
    public void onDisconnected(String reason) {
        connectionService.handleDisconnect(reason);
    }
}
