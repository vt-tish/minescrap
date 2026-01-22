package com.vttish.minescrap;

import com.vttish.minescrap.api.MinecraftBot;
import com.vttish.minescrap.api.event.Events;
import com.vttish.minescrap.factory.MinecraftBotFactory;
import com.vttish.minescrap.factory.MinecraftBotVersion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MinecraftBot bot = MinecraftBotFactory.create(
                MinecraftBotVersion.v1_16_5,
                "PerdanY_2",
                "play.funtime.su"
        );

        // bot.on(Events.Chat.class, System.out::println);
        bot.once(Events.Join.class, () -> {
            System.out.println("Username: " + bot.getUsername());
            System.out.println("UUID: " + bot.getPlayer().getUuid());
            System.out.println("EntityId: " + bot.getPlayer().getEntityId());
        });

        bot.connect();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            if (inp.equals("#disc")) {
                bot.disconnect();
                break;
            }

            bot.chat(inp);
        }
    }
}
