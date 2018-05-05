package lx.gs.event;

import gs.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jin Shuai
 */
public enum EventModule implements Module {
    INSTANCE;

    private final Map<Integer, List<EventListener>> listrenerMap = new HashMap<>();

    @Override
    public void start() {
    }

    public void registerListener(List<Integer> eventTypes, EventListener listener) {
        eventTypes.forEach(eventType -> registerListener(eventType, listener));
    }


    public void registerListener(int eventType, EventListener listener) {
        getListeners(eventType).add(listener);
    }

    /**
     * @param event
     */
    public void broadcastEvent(AbstractEvent event) {
        getListeners(event.eventType).forEach(listener -> listener.onEvent(event));
    }

    private List<EventListener> getListeners(int type) {
        if (!listrenerMap.containsKey(type)) {
            listrenerMap.putIfAbsent(type, new ArrayList<>());
        }
        return listrenerMap.get(type);
    }
}
