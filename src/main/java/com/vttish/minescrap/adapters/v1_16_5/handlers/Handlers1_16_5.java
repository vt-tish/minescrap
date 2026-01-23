package com.vttish.minescrap.adapters.v1_16_5.handlers;

import com.vttish.minescrap.core.network.NetworkClient;
import com.vttish.minescrap.core.service.CoreServices;

public class Handlers1_16_5 {
    public final JoinGameHandler1_16_5 joinGameHandler1_16_5;
    public final MovementHandler1_16_5 movementHandler1_16_5;

    public Handlers1_16_5(NetworkClient networkClient, CoreServices coreServices) {
        this.joinGameHandler1_16_5 = new JoinGameHandler1_16_5(networkClient, coreServices.connectionService);
        this.movementHandler1_16_5 = new MovementHandler1_16_5(networkClient, coreServices.playerService);
    }
}
