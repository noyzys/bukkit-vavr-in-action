package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.LinearSeq;
import io.vavr.collection.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/* LinearSequence :: immutable, linear sequences */
public final class VavrLinearSequence implements IBukkitCommandExecutor {

    /**
     * Example usage of {@link LinearSeq},
     * Adds a predefined list of {@link ItemStack}s to the specified player's inventory.
     *
     * @param player The player whose inventory will receive the items.
     */
    @Override
    public void execute(final Player player, final String[] args) {

        final LinearSeq<ItemStack> itemStacks = List.of(
                new ItemStack(Material.STONE), new ItemStack(Material.DIRT),
                new ItemStack(Material.BEDROCK));

        final PlayerInventory inventory = player.getInventory();
        itemStacks.forEach(inventory::addItem);
    }

    @Override
    public String getName() {
        return "linearsequence";
    }
}
