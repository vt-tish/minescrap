package com.vttish.minescrap.api.event;

public class Subscription {
    private final Runnable unsubscribeAction;

    public Subscription(Runnable unsubscribeAction) {
        this.unsubscribeAction = unsubscribeAction;
    }

    public void unsubscribe() {
        unsubscribeAction.run();
    }
}
