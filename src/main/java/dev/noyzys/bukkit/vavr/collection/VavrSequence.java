package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import org.bukkit.entity.Player;

/* Sequence :: immutable sequential data structures. */
public final class VavrSequence implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrSequence(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    @Override
    public void execute(final Player player, final String[] args) {
        final Seq<Player> onlinePlayers = onlinePlayers();
        onlinePlayers.forEach(it -> player.sendMessage(it.getName() + " is online"));
    }

    /**
     * Retrieves the list of currently online players from the server.
     *
     * @return a sequence of online players
     */
    private Seq<Player> onlinePlayers() {
        return List.ofAll(vavrPlugin.getServer().getOnlinePlayers());
    }

    @Override
    public String getName() {
        return "sequence";
    }
}
