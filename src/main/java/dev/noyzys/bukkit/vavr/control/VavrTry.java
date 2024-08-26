package dev.noyzys.bukkit.vavr.control;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.bukkit.entity.Player;

/* Try :: The Try control gives us the ability write safe code without focusing on try-catch blocks in the presence of exceptions */
public final class VavrTry implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrTry(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Teleport one player to the location of another player in a Minecraft server.
     * This method uses Vavr library's Option and Try classes for functional-style operations.
     *
     * @param args The command-line arguments, expected to contain at least two elements with player names.
     * @param player The player who is executing the command.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        if (args.length < 2) {
            player.sendMessage("> Usage: teleport <targetPlayer> <destinationPlayer>");
            return;
        }

        final String targetPlayer = args[0];
        final String destinationPlayer = args[1];

        final Option<Player> targetPlayerOpt = Option.of(vavrPlugin.getServer().getPlayer(targetPlayer));
        final Option<Player> destinationPlayerOpt = Option.of(vavrPlugin.getServer().getPlayer(destinationPlayer));

        if (targetPlayerOpt.isEmpty()) {
            player.sendMessage("> Target player not found.");
            return;
        }

        if (destinationPlayerOpt.isEmpty()) {
            player.sendMessage("> Destination player not found.");
            return;
        }

        final Player targetPlayers = targetPlayerOpt.get();
        final Player destinationPlayers = destinationPlayerOpt.get();

        Try<Void> teleportResult = Try.run(() -> targetPlayers.teleport(destinationPlayers.getLocation()));
        teleportResult.onFailure(ex -> player.sendMessage("> Teleportation failed: " + ex.getMessage()))
                .onSuccess(v -> player.sendMessage("> Successfully teleported " + targetPlayer + " to " + destinationPlayer));
    }

    @Override
    public String getName() {
        return "try";
    }
}