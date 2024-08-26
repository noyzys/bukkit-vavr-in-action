package dev.noyzys.bukkit.vavr.concurrent;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.concurrent.Future;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/* Future :: A Future is a computation result that becomes available at some point */
public final class VavrFuture implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrFuture(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Updates the location of the player asynchronously and displays a broadcast message.
     *
     * @param player The player whose location is to be updated and displayed.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Future<Void> futureCompletion = Future.run(() -> {
            final String name = player.getName();
            final String location = player.getLocation().toString();

            displayPlayerLocation(name, location);
        });

        futureCompletion.onFailure(Throwable::printStackTrace);

        final Future<String> messageFuture = Future.of(() -> "Hi " + player.getName());
        messageFuture.onComplete(result -> {
           result.peek(message -> vavrPlugin.getServer().broadcastMessage(message))
                   .onFailure(Throwable::printStackTrace);
        });

        final Future<Location> futureLocation = getPlayerLocationAsync(player);
        futureLocation.onSuccess(location -> player.sendMessage("> Player is at: " + location.toString() + " location"));
    }

    /**
     * Displays the player's location asynchronously.
     *
     * @param name The name of the player.
     * @param location The location of the player.
     */
    private void displayPlayerLocation(final String name, final String location) {
        Future.run(() -> System.out.println("Player " + name + " is at " + location))
                .onFailure(Throwable::printStackTrace);
    }

    /**
     * Retrieves the player's location asynchronously.
     *
     * @param player The player whose location is to be retrieved.
     * @return A Future object representing the asynchronous computation of the player's location.
     */
    private Future<Location> getPlayerLocationAsync(final Player player) {
        return Future.of(player::getLocation);
    }

    @Override
    public String getName() {
        return "future";
    }
}
