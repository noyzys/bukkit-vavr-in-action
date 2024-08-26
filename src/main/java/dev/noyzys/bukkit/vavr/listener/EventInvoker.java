package dev.noyzys.bukkit.vavr.listener;

import org.bukkit.event.Event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * The EventInvoker annotation is used to indicate that the annotated method
 * is intended to act as an invoker for a specific type of event.
 *
 * <p>This annotation can only be applied to methods and must be retained at runtime
 * to allow for reflection to be used to identify and invoke methods marked by it.
 *
 * <p>The {@code value} element specifies the class of the event that the
 * annotated method is designed to handle. The event class should extend from
 * the {@code Event} superclass.
 *
 * <p>Usage example:
 *
 * <pre>{@code
 * @EventInvoker(MyCustomEvent.class)
 * void handleEvent(MyCustomEvent event) {
 * // event handling logic
 * }
 * }</pre>
 *
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.RetentionPolicy
 * @see java.lang.annotation.Target
 * @see ElementType
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventInvoker {

    /**
     * Specifies the class of the event that this method should handle.
     *
     * @return the class object of the event type
     */
    Class<? extends Event> value();
}