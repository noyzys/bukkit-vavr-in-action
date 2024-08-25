package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import org.bukkit.entity.Player;

/* Iterator :: iterate once over a sequence of elements */
public final class VavrIterator implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrIterator(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Example usage of {@link Iterator},
     * Sends predefined messages to all online players.
     * <p>
     * Iterate through list of words and send each word as a message.
     * Iterate through list of online players and send a greeting message to each player.
     *
     * @param player the player object to send messages from
     */
    @Override
    public void execute(final Player player, final String[] args) {
        // Iterable<Player> sends message to all online players
        final List<String> words = List.of("pure", "fun", "purely", "function");
        final Iterator<String> iterator = words.iterator();

        iterator.forEach(player::sendMessage);

        Iterator<? extends Player> players = Iterator.ofAll(vavrPlugin.getServer().getOnlinePlayers());
        players.forEach(itPlayer -> player.sendMessage("Hi" + itPlayer.getName()));
    }

    @Override
    public String getName() {
        return "iterator";
    }
}
