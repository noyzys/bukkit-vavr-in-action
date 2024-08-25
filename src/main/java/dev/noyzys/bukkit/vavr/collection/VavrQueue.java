package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.Queue;
import org.bukkit.entity.Player;

/* Queue :: An immutable Queue stores elements allowing a first-in-first-out (FIFO) retrieval */
public final class VavrQueue implements IBukkitCommandExecutor {

    private final Queue<String> queuePlayers = Queue.empty();
    private static final int MAX_SIZE = 10;

    /**
     * Adds a player's name to the queue. If the queue has reached its
     * maximum size, it removes the first player before adding the new one.
     *
     * @param player the player object from which the name will be retrieved
     */
    @Override
    public void execute(final Player player, final String[] args) {
        addToQueue(player.getName(), queuePlayers, MAX_SIZE);
    }

    /**
     * Adds a player's name to the queue. If the queue has reached its
     * maximum size, it removes the first player before adding the new one.
     *
     * @param name the player's name to add to the queue
     * @param queue the queue to which the player's name should be added
     * @param maxSize the maximum number of elements that the queue can hold
     */
    private void addToQueue(final String name, Queue<String> queue,
                            final int maxSize) {
        if (queue.length() >= maxSize) {
            queue = queue.dequeue()._2;
        }

        queue.append(name);
    }

    @Override
    public String getName() {
        return "queue";
    }
}
