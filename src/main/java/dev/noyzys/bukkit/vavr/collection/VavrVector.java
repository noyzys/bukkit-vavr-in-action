package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.Vector;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/* Vector :: Vector is the default Seq implementation */
public final class VavrVector implements IBukkitCommandExecutor {

    private final Vector<ItemStack> itemStacks = Vector.empty();

    /**
     * Executes the command by adding a stack of 10 diamonds to the player's inventory.
     *
     * @param player the player whose inventory is to be modified.
     * @param args the command arguments (ignored in this implementation).
     */
    @Override
    public void execute(final Player player, final String[] args) {
        itemStacks.append(new ItemStack(Material.DIAMOND, 10));
        itemStacks.forEach(itemStack -> player.getInventory().addItem(itemStack));
    }

    @Override
    public String getName() {
        return "vector";
    }
}