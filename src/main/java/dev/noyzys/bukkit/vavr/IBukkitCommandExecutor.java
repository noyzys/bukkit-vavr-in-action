package dev.noyzys.bukkit.vavr;

import io.vavr.control.Option;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents a command executor in the Bukkit API, specifically designed for handling
 * commands that are received through the Bukkit server.
 * Implementing this interface allows an object to directly handle specific types of commands.
 */
public interface IBukkitCommandExecutor extends CommandExecutor {

    /**
     * Executes the given command, returning its success.
     *
     * @param sender Source of the command.
     * @param command Command which was executed.
     * @param arg Alias of the command which was used.
     * @param args Passed command arguments.
     * @return true if a valid player executed the command, else false.
     */
    @Override
    default boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        return findSender(sender)
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .peek(player -> execute(player, args))
                .isDefined();
    }

    /**
     * Attempts to find and return the CommandSender if they are a player.
     *
     * @param sender The sender to validate and return.
     * @return An Optional containing the sender if they are a player, otherwise an empty Optional.
     */
    private Option<CommandSender> findSender(final CommandSender sender) {
        return Option.of(sender);
    }

    /**
     * Executes the command action for the player.
     *
     * @param player The player executing the command.
     * @param args The arguments provided with the command.
     */
    void execute(final Player player, final String[] args);

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    String getName();
}
