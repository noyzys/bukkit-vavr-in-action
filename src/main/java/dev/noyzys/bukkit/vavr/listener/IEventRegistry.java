package dev.noyzys.bukkit.vavr.listener;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The {@code IEventRegistry} interface defines operations for subscribing to events and calling an event.
 * Implementations of this interface should provide mechanisms for registering event listeners and triggering events.
 */
interface IEventRegistry {

    /**
     * Subscribe to events using the provided listeners. This method allows a plugin to register multiple event
     * listeners at once.
     *
     * @param plugin The {@link JavaPlugin} instance that is registering the listeners. This is often used for
     * context or for identifying the source of the event subscriptions.
     * @param listeners Variadic parameter to accept multiple {@link Listener} instances. Each listener should
     * correspond to an event type that it is interested in listening to.
     */
    void subscribeToEvents(final JavaPlugin plugin, final Listener... listeners);

    /**
     * Call or trigger an event. This method is responsible for invoking the event across all subscribed listeners
     * that are interested in the event type.
     *
     * @param event The {@link Event} instance to be triggered. This object contains the details of the event,
     * including any relevant data that listeners might use.
     */
    void callEvent(final Event event);
}
