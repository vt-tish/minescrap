package com.vttish.minescrap.core.event;

import com.vttish.minescrap.api.event.Listenable;
import com.vttish.minescrap.api.event.Subscription;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventRegister {
    private final Map<Class<? extends Listenable>, List<Listenable>> listeners = new ConcurrentHashMap<>();
    private final Set<Listenable> onceListeners = ConcurrentHashMap.newKeySet();

    public <T extends Listenable> Subscription addListener(Class<T> event, T listener) {
        addHelper(event, listener);

        return new Subscription(() -> removeListener(event, listener));
    }

    public <T extends Listenable> Subscription addOnceListener(Class<T> event, T listener) {
        addHelper(event, listener);
        onceListeners.add(listener);

        return new Subscription(() -> removeListener(event, listener));
    }

    @SuppressWarnings("unchecked")
    public <T extends Listenable> void notifyListeners(Class<T> event, Consumer<T> action) {
        List<Listenable> list = listeners.get(event);

        if (list != null) {
            for (T listener : (List<T>)list) {
                try {
                    action.accept(listener);

                    if (onceListeners.contains(listener)) {
                        removeListener(event, listener);
                        onceListeners.remove(listener);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    private <T extends Listenable> void removeListener(Class<T> event, T listener) {
        List<Listenable> list = listeners.get(event);

        if (list != null) {
            list.remove(listener);
        }
    }

    private <T extends Listenable> void addHelper(Class<T> event, T listener) {
        listeners.computeIfAbsent(event, key -> new CopyOnWriteArrayList<>()).add(listener);
    }
}
