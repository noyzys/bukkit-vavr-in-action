package dev.noyzys.bukkit.vavr.struct;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/* Tuple :: base interface of all tuples */
public final class VavrTuplet implements IBukkitCommandExecutor {

    /**
     * This method demonstrates how to use the Vavr library's Tuple classes to manipulate
     * player location data. It retrieves the player's current location, modifies it, and
     * sends the original and modified location information back to the player.
     *
     * @param player the player whose location information is to be manipulated and displayed
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Location location = player.getLocation();

        final Tuple3<Double, Double, Double> playerLocation = Tuple.of(location.getX(), location.getY(), location.getZ());
        final Tuple3<Double, Double, Double> mapperLocation = playerLocation
                .map1(x -> x + 1)
                .map2(y -> y + 1)
                .map3(z -> z + 1);

        player.sendMessage("> Player location: " + mapperLocation);
        player.sendMessage("> Player joined at: " + playerLocation._1 + ", " + playerLocation._2 + ", " + playerLocation._3);
    }

    @Override
    public String getName() {
        return "tuple";
    }
}