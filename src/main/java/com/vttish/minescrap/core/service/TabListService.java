package com.vttish.minescrap.core.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TabListService {
    private final Map<UUID, String> tabList = new ConcurrentHashMap<>();

    public void addEntry(UUID uuid, String username) {
        if (username == null || username.isEmpty()) {
            return;
        }

        tabList.put(uuid, username);
    }

    public void remove(UUID uuid) {
        tabList.remove(uuid);
    }

    public String getName(UUID uuid) {
        return tabList.getOrDefault(uuid, uuid.toString());
    }

    public int getCount() {
        return tabList.size();
    }
}
