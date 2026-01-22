package com.vttish.minescrap.adapters.v1_16_5.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;

import java.util.LinkedList;
import java.util.Queue;

public class ComponentHelper {
    public static String toPlainText(Component component) {
        StringBuilder result = new StringBuilder();

        Queue<Component> queue = new LinkedList<>();
        queue.add(component);

        while (!queue.isEmpty()) {
            Component front = queue.remove();

            queue.addAll(front.children());

            if (front instanceof TextComponent) {
                result.append(((TextComponent) front).content());
            }

            if (front instanceof TranslatableComponent) {
                result.append(((TranslatableComponent) front).key());
            }
        }

        return result.toString();
    }
}
