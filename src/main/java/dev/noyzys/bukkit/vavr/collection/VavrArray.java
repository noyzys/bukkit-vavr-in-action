package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.Array;
import io.vavr.control.Option;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/* Array :: Traversable wrapper for Object[] */
public final class VavrArray implements IBukkitCommandExecutor {

    @Override
    public void execute(final Player player, final String[] args) {
        final PlayerInventory inventory = player.getInventory();

        inventory.addItem(new ItemStack(Material.DIRT, 49));
        removeItem(player, Material.DIRT, 10);
    }

    /**
     * Removes a specific amount of an item (material) from a player's inventory.
     *
     * @param player The player whose inventory will be modified.
     * @param material The material to remove from the inventory.
     * @param amount The amount of the material to remove.
     */
    private void removeItem(final Player player, final Material material, final int amount) {
        final PlayerInventory inventory = player.getInventory();

        Array.of(inventory.getContents())
                .filter(itemStack -> Option.of(itemStack)
                        .exists(stack -> stack.getType() == material))
                .forEach(itemStack -> {
                    if (itemStack.getAmount() >= amount) {
                        itemStack.setAmount(itemStack.getAmount() - amount);
                    }

                    inventory.removeItem(itemStack);
                });
    }

    @Override
    public String getName() {
        return "array";
    }
}
