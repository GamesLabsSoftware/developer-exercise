package net.gameslabs.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentRegistry {

    private List<Component> componentList;

    public ComponentRegistry() {
        componentList = new ArrayList<>();
    }

    public void registerComponent(Component component) {
        componentList.add(component);
    }

    public void sendEvent(Event event) {
        List<EventMethod> methods = componentList.stream().map(component -> component.getEvents(event)).flatMap(Collection::stream).collect(Collectors.toList());
        for (EventMethod eventMethod : methods) {
            // Would require proper handling in a prodution environement
            eventMethod.onExecute(event);
            if (event.isCancelled()) {
                break;
            }
        }
    }

    /**
     * Loads all components
     */
    public void load() {
        componentList.forEach(component -> component.initialize(this));
        componentList.forEach(Component::onLoad);
    }

    /**
     * Unloads all components
     */
    public void unload() {
        componentList.forEach(Component::onUnload);
    }
}
