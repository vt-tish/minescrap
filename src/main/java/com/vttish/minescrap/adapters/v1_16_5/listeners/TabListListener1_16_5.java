package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.data.game.PlayerListEntry;
import com.github.steveice10.mc.protocol.data.game.PlayerListEntryAction;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import com.vttish.minescrap.adapters.v1_16_5.util.ComponentHelper;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.TabListService;

public class TabListListener1_16_5 implements PacketListener {
    private final TabListService tabListService;

    public TabListListener1_16_5(TabListService tabListService) {
        this.tabListService = tabListService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerPlayerListEntryPacket p) {
            handlePlayerListEntry(p);
        }
    }

    private void handlePlayerListEntry(ServerPlayerListEntryPacket packet) {
        PlayerListEntryAction action = packet.getAction();

        for (PlayerListEntry entry : packet.getEntries()) {
            if (action == PlayerListEntryAction.ADD_PLAYER) {
                tabListService.addEntry(
                        entry.getProfile().getId(),
                        entry.getProfile().getName()
                );
            } else if (action == PlayerListEntryAction.REMOVE_PLAYER) {
                tabListService.remove(entry.getProfile().getId());
            }
        }
    }
}
