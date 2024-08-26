package dev.noyzys.bukkit.vavr.control;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/* Either :: Either represents a value of two possible types */
public final class VavrEither implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrEither(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Handles incoming game mode change requests.
     *
     * @param player The player issuing the change request.
     * @param args Command arguments, should contain the target player's name and the target game mode.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Either<String, String> validResult = checkPlayerPermission(player);

        validResult.peek(sucess -> player.sendMessage("> You have permission" + sucess))
                .peekLeft(error -> player.sendMessage("You do not have permission" + error));

        // GameMode
        if (args.length < 2) {
            return;
        }

        final String name = args[0];
        final String mode = args[1];

        final Either<String, String> resultGameMode = findPlayer(name)
                .flatMap(it -> findGameMode(mode)
                        .map(gameMode -> changeGameMode(it, gameMode))
                        .getOrElseGet(Either::left));

        resultGameMode.peek(player::sendMessage)
                .peekLeft(player::sendMessage);
    }

    /**
     * Finds a player by their name.
     *
     * @param name the name of the player
     * @return an Either the player if found, or an error message if not found
     */
    private Either<String, Player> findPlayer(final String name) {
        return Try.of(() -> vavrPlugin.getServer().getPlayer(name))
                .toEither("> Player not found" + name);
    }

    /**
     * Finds a GameMode by its name.
     *
     * @param mode the name of the game mode
     * @return an Either with the GameMode if valid, or an error message if invalid
     */
    private Either<String, GameMode> findGameMode(final String mode) {
        return Try.of(() -> GameMode.valueOf(mode.toUpperCase()))
                .toEither("> Invalid gamemode: " + mode);
    }

    /**
     * Changes the game mode of the specified player to the specified game mode.
     *
     * @param player the player whose game mode is to be changed
     * @param gameMode the new game mode to set
     * @return an Either with a success message if changed successfully, or an error message if not
     */
    private Either<String, String> changeGameMode(final Player player, final GameMode gameMode) {
        player.setGameMode(gameMode);
        return Either.right("> Changed gamemode of " + player.getName() + " to " + gameMode);
    }

    /**
     * Checks if a player has the required permission.
     *
     * @param player the player to check
     * @return an Either containing an error message or a success message
     */
    private Either<String, String> checkPlayerPermission(final Player player) {
        return player.hasPermission("hujson.hujson")
                ? Either.right("hujson.hujson")
                : Either.left("> Missing hujson.hujson");
    }

    @Override
    public String getName() {
        return "either";
    }
}