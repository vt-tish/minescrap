package com.vttish.minescrap.core;

import com.vttish.minescrap.api.MinescrapBot;
import com.vttish.minescrap.api.MinescrapBotConfig;
import io.avaje.inject.BeanScope;
import io.avaje.inject.InjectModule;

@InjectModule(requires = MinescrapBotConfig.class)
public class MinescrapBotBootstrap {
    private MinescrapBotBootstrap() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("resource")
    public static MinescrapBot create(MinescrapBotConfig config) {
        BeanScope scope = BeanScope.builder()
                .bean(MinescrapBotConfig.class, config)
                .build();

        return scope.get(DefaultMinescrapBot.class);
    }
}
