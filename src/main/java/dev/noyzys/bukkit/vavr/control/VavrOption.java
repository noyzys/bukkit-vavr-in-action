package dev.noyzys.bukkit.vavr.control;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.control.Option;
import org.bukkit.entity.Player;

/* Option :: Option is a monadic container type which represents an optional value */
public final class VavrOption implements IBukkitCommandExecutor {

    /**
     * Toggles the flying state for a player if they have the permission.
     * Sends a message to the player about the lack of permission if they do not have it.
     *
     * @param player The player whose flying state is to be toggled.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        Option.of(player.hasPermission("hujson.flight"))
                .filter(hasPermission -> hasPermission)
                .map(hasPermission -> toggleFly(player))
                .onEmpty(() -> {
                    player.sendMessage("> You do not have permission to use this command");
                });
    }

    /**
     * Toggles the flying state of the player and sends them a message about the new state.
     *
     * @param player The player whose flying state is to be toggled.
     * @return true after toggling the flying state.
     */
    private boolean toggleFly(final Player player) {
        final boolean isFlying = player.getAllowFlight();
        player.setAllowFlight(!isFlying);
        player.sendMessage("> Flying mode " + ((isFlying ? "disabled" : "enabled")));
        return true;
    }

    @Override
    public String getName() {
        return "option";
    }
}