package dev.noyzys.bukkit.vavr.listener;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;

/**
 * This class is responsible for registering event listeners. It implements the {@link IEventRegistry}
 * interface, allowing it to integrate with systems that require dynamic event listener registration.
 * It holds a definitive state by being final, implying that it should not be subclassed.
 * This design is useful when the class encapsulation should not be compromised and when it is important
 * to guarantee the behavior of the class to external clients.
 */
public final class EventListenerRegistrar implements IEventRegistry {

    private final BukkitVavrPlugin vavrPlugin;

    public EventListenerRegistrar(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Registers all methods annotated with {@link EventInvoker} in the supplied listeners as event handlers.
     *
     * @param plugin    the plugin to which the listeners belong
     * @param listeners varargs parameter of listeners that potentially contain {@link EventInvoker} annotated methods
     */
    @Override
    public void subscribeToEvents(final JavaPlugin plugin, final Listener... listeners) {
        final Class<?> clazz = listeners.getClass();
        final List<Method> methods = List.of(clazz.getDeclaredMethods());

        methods.forEach(method -> Try.run(() -> subscribeToEvents(plugin, method, listeners))
                .onFailure(Throwable::printStackTrace));
    }

    /**
     * Helper method to register a single method as an event listener.
     *
     * @param plugin    the plugin instance
     * @param method    the method to be registered
     * @param listeners the array of listener instances
     */
    private void subscribeToEvents(final JavaPlugin plugin, final Method method, final Listener... listeners) {
        if (method.isAnnotationPresent(EventInvoker.class)) {
            EventInvoker eventInvoker = method.getAnnotation(EventInvoker.class);
            final Class<? extends Event> eventClazz = eventInvoker.value();

            for (final Listener listener : listeners) {
                plugin.getServer().getPluginManager().registerEvent(eventClazz, listener, EventPriority.NORMAL,
                        (listeningEvent, event) -> Try.run(() -> method.invoke(listeners, event))
                                .onFailure(Throwable::printStackTrace), plugin
                );
            }
        }
    }

    /**
     * Calls an event using the server's plugin manager.
     *
     * This method is typically used within a plugin to trigger custom or built-in
     * events within the Minecraft server. It utilizes VavrPlugin's ability to
     * interact with the server's plugin management system.
     *
     * @param event the event to be called. This is an object of type Event,
     * which should be appropriately instantiated before calling this method.
     */
    @Override
    public void callEvent(final Event event) {
        vavrPlugin.getServer().getPluginManager().callEvent(event);
    }
}
