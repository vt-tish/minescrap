package com.vttish.minescrap.api;

import com.vttish.minescrap.api.entity.EntityManager;
import com.vttish.minescrap.api.entity.EntityRegistry;
import com.vttish.minescrap.api.entity.capability.CapabilityFactory;
import com.vttish.minescrap.core.MinescrapBotBootstrap;

import java.util.concurrent.TimeUnit;

public interface MinescrapBot {
    void connect();
    void disconnect();
    boolean isConnected();

    EntityRegistry entityRegistry();
    CapabilityFactory capabilityFactory();
    EntityManager entityManager();

    MinescrapBotConfig config();

    static MinescrapBot create(MinescrapBotConfig config) {
        return MinescrapBotBootstrap.create(config);
    }

    static Builder builder(String username) {
        return new Builder(username);
    }

    class Builder {
        private final String username;
        private String host = "localhost";
        private int port = 25565;
        private String clientBrand = "vanilla";
        private int viewDistance = 10;
        private long connectTimeoutMillis = 10000;

        private Builder(String username) {
            this.username = username;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder clientBrand(String clientBrand) {
            this.clientBrand = clientBrand;
            return this;
        }

        public Builder viewDistance(int viewDistance) {
            this.viewDistance = viewDistance;
            return this;
        }

        public Builder connectTimeout(long duration, TimeUnit unit) {
            this.connectTimeoutMillis = unit.toMillis(duration);
            return this;
        }

        public MinescrapBot build() {
            MinescrapBotConfig config = new MinescrapBotConfig(
                    username,
                    host,
                    port,
                    clientBrand,
                    viewDistance,
                    connectTimeoutMillis
            );

            return MinescrapBotBootstrap.create(config);
        }
    }
}
