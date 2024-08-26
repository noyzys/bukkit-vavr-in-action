package dev.noyzys.bukkit.vavr.struct;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static io.vavr.API.*;

/* Match :: core types like (Checked)Functions and Tuples */
public final class VavrMatch implements IBukkitCommandExecutor {

    /**
     * Handles the incoming command for a player. It matches the command against predefined cases
     * and executes the corresponding action.
     *
     * @param player The player object on which the command is to be executed.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        Match(args[0]).of(Case($("send"), () -> send(player)),
                Case($("give"), () -> giveItem(player)),
                Case($(), () -> {
                    player.sendMessage("> Unknown command. Usage: /match <send|give>");
                    return true;
                })
        );
    }

    /**
     * Sends a predefined message to the player.
     *
     * @param player The player to send the message to.
     * @return true to indicate the operation was successful.
     */
    private boolean send(final Player player) {
        player.sendMessage("^^");
        return true;
    }

    /**
     * Gives a predefined item to the player.
     *
     * @param player The player to which the item will be given.
     * @return true to indicate the operation was successful.
     */
    private boolean giveItem(final Player player) {
        final ItemStack itemStack = new ItemStack(Material.APPLE, 16);
        final PlayerInventory inventory = player.getInventory();

        inventory.addItem(itemStack);
        return true;
    }

    @Override
    public String getName() {
        return "match";
    }
}