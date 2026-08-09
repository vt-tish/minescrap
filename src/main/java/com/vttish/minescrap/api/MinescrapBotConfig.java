package com.vttish.minescrap.api;

public record MinescrapBotConfig(
        String username,
        String host,
        int port,
        String clientBrand,
        int viewDistance,
        long connectTimeoutMillis
) {
}
