package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Vector;
import io.vavr.control.Try;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/* IndexedSequence :: immutable, indexed sequences */
public final class VavrIndexedSequence implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrIndexedSequence(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    @Override
    public void execute(final Player player, final String[] args) {
        // sends message to the player about the online status
        player.sendMessage(onlinePlayers().isEmpty()
                ? "> Offline players"
                : "> Online Players: " + onlinePlayers().mkString(", "));

        final IndexedSeq<String> welcomeMessages = Vector.of(
                "Hello",
                "Hi",
                "Siema",
                "Guten",
                "Elo"
        );

        welcomeMessages.forEach(message -> player.sendMessage(ChatColor.GREEN + message));
        onlinePlayers().forEach(s -> Try.run(() -> player.sendMessage("Hi")));
    }

    /**
     * Example usage of {@link IndexedSeq},
     * Retrieves a list of the names of all online players on the server.
     * Utilizes the Vavr library's IndexedSeq and Vector data structures to store
     *
     * @return an IndexedSeq containing the names of the online players.
     * */
    private IndexedSeq<String> onlinePlayers() {
        return Vector.ofAll(vavrPlugin.getServer().getOnlinePlayers())
                .map(Player::getName);
    }

    @Override
    public String getName() {
        return "indexedsequence";
    }
}
