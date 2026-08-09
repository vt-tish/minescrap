package com.vttish.minescrap.core;

import com.vttish.minescrap.api.MinescrapBot;
import com.vttish.minescrap.api.MinescrapBotConfig;

public class MinescrapBotBootstrap {
    private MinescrapBotBootstrap() {
        throw new UnsupportedOperationException();
    }

    public static MinescrapBot create(MinescrapBotConfig config) {
        return DaggerMinescrapBotComponent.factory().create(config).getBot();
    }
}
