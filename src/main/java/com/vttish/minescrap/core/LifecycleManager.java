package com.vttish.minescrap.core;

import io.avaje.inject.BeanScope;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class LifecycleManager {
    private final BeanScope scope;
    private final Thread shutdownHook;

    @Inject
    public LifecycleManager(BeanScope scope) {
        this.scope = scope;
        this.shutdownHook = new Thread(scope::close);

        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public void shutdown() {
        scope.close();

        try {
            Runtime.getRuntime().removeShutdownHook(shutdownHook);
        } catch (Exception ignored) {}
    }
}
