package dev.noyzys.bukkit.vavr.control;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/* Tree :: An Impl similar to scalaz's Validation control */
public final class VavrValidation implements IBukkitCommandExecutor {

    /**
     * Validates the player's location and sends a message to the player
     * with the result of the validation.
     *
     * @param player The player whose location is to be validated.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Either<List<String>, Location> result = playerLocation(player);

        result.peek(location -> player.sendMessage("> Your location is valid: " + location.toString()))
                .peekLeft(errors -> player.sendMessage("> Your location is invalid due to: " + errors.mkString(", ")));
    }

    /**
     * Retrieves the player's location after validation.
     *
     * @param player The player whose location is to be retrieved.
     * @return An Either containing a valid Location or a List of error messages.
     */
    private Either<List<String>, Location> playerLocation(final Player player) {
        return validateLocation(player).toEither();
    }

    /**
     * Validates the location of a given player.
     *
     * @param player The player whose location is to be validated.
     * @return A Validation containing either a valid Location or a list of error messages.
     */
    private Validation<List<String>, Location> validateLocation(final Player player) {
        final Location location = player.getLocation();
        List<String> errors = List.empty();

        if (location.getX() < -2500 || location.getX() > 2500) {
            errors = errors.append("X coordinate out of bounds");
        }
        if (location.getY() < 0 || location.getY() > 256) {
            errors = errors.append("Y coordinate out of bounds");
        }
        if (location.getZ() < -30000 || location.getZ() > 2500) {
            errors = errors.append("Z coordinate out of bounds");
        }

        return errors.isEmpty() ? Validation.valid(location) : Validation.invalid(errors);
    }

    @Override
    public String getName() {
        return "validation";
    }
}
