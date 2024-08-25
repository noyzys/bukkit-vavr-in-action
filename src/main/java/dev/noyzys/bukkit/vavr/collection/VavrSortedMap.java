package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.TreeMap;
import org.bukkit.entity.Player;

import java.util.Comparator;

/* SortedMap :: immutable sorted map */
public final class VavrSortedMap implements IBukkitCommandExecutor {

    private final TreeMap<Integer, String> topPlayers = TreeMap.empty(Comparator.reverseOrder());

    @Override
    public void execute(final Player player, final String[] args) {
        final int playerExp = getPlayerExp(player);

        topPlayers.put(playerExp, player.getName());
        ensureOnlyTopTenPlayers();

        player.sendMessage("> TOP 10 PLAYERS: ");
        topPlayers.forEach((score, name) -> player.sendMessage(name + ":" + score));
    }

    /**
     * Ensures that the leaderboard only contains the top 10 players. If there are more
     * than 10 players, it removes the players with the lowest experience points.
     */
    private void ensureOnlyTopTenPlayers() {
        if (topPlayers.size() <= 10) {
            return;
        }

        topPlayers.drop(topPlayers.size() - 10);
    }

    /**
     * Retrieves the experience points for the given player.
     *
     * @param player The player whose experience points to retrieve.
     * @return The experience points of the given player.
     */
    private int getPlayerExp(final Player player) {
        return player.getExpToLevel();
    }


    @Override
    public String getName() {
        return "sortedmap";
    }
}
