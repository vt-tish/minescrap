package com.vttish.minescrap.adapters.v1_16_5.listeners;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.vttish.minescrap.adapters.v1_16_5.util.ComponentHelper;
import com.vttish.minescrap.core.network.PacketListener;
import com.vttish.minescrap.core.service.ChatService;

public class ChatListener1_16_5 implements PacketListener {
    private final ChatService chatService;

    public ChatListener1_16_5(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void onPackedReceived(Object packet) {
        if (packet instanceof ServerChatPacket p) {
            chatService.handleChat(ComponentHelper.toPlainText(p.getMessage()));
        }
    }
}
