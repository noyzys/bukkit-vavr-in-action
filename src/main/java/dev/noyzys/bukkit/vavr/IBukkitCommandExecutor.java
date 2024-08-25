package dev.noyzys.bukkit.vavr;

import io.vavr.control.Option;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IBukkitCommandExecutor extends CommandExecutor {

    @Override
    default boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        return findSender(sender)
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .peek(player -> execute(player, args))
                .isDefined();
    }

    private Option<CommandSender> findSender(final CommandSender sender) {
        return Option.of(sender);
    }

    void execute(final Player player, final String[] args);

    String getName();
}
