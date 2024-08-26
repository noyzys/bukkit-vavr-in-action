package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.Stream;
import org.bukkit.entity.Player;

/* Stream :: An immutable Stream is lazy sequence of elements which may be infinitely long */
public final class VavrStream implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrStream(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Filters and displays names of all online players excluding those whose names start with "Buz".
     * This method assumes it is part of a class that has access to a server and a player context.
     *
     * @param player The player to whom messages will be sent.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Stream<String> names = Stream.ofAll(vavrPlugin.getServer().getOnlinePlayers())
                .map(Player::getName)
                .filter(name -> !name.startsWith("Buz"));

        names.forEach(name -> player.sendMessage("> Player: " + name));
    }

    @Override
    public String getName() {
        return "stream";
    }
}
