package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/* Mapper :: key-value store mapper with HAMT */
public final class VavrMapper implements IBukkitCommandExecutor {

    /* A map of reward items and amount. */
    private final Map<Material, Integer> rewards = HashMap.of(
            Material.BEDROCK, 20,
            Material.DIAMOND_BLOCK, 20,
            Material.GOLD_BLOCK, 40,
            Material.DIAMOND_AXE, 1
    );

    @Override
    public void execute(final Player player, final String[] args) {
        rewards.put(Material.BEACON, 43);
        giveRewardItems(player);
    }

    /**
     * Gives the reward items to the player.
     *
     * @param player The player to receive the reward items.
     */
    private void giveRewardItems(final Player player) {
        rewards.forEach((material, amount) -> createAndAddItemStack(player, material, amount));
    }

    /**
     * Creates an ItemStack with the specified material and amount and adds it to the player's inventory.
     *
     * @param player The player who will receive the item.
     * @param material The material of the item to be created.
     * @param amount The amount of the item to be created.
     */
    private void createAndAddItemStack(final Player player, final Material material, final int amount) {
        final ItemStack itemStack = new ItemStack(material, amount);

        final PlayerInventory inventory = player.getInventory();
        inventory.addItem(itemStack);
    }

    @Override
    public String getName() {
        return "map";
    }
}
