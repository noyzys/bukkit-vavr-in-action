package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.List;
import org.bukkit.entity.Player;

/* Ordered :: An ordered collection */
public final class VavrOrdered implements IBukkitCommandExecutor {

    private List<PointsImpl> points;

    /**
     * Sorts the list of {@code PointsImpl} instances in ascending order of points and sends their
     * string representation to the specified player.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final List<PointsImpl> sortedPoints = points.sorted(PointsImpl::compareTo);
    
        sortedPoints.forEach(playerPoints -> player.sendMessage(playerPoints.toString()));
    }

    @Override
    public String getName() {
        return "ordered";
    }
}

/**
 * This class represents an implementation of points featuring a name and a point value.
 * It supports comparison and a custom string representation.
 */
record PointsImpl(String name, int points) implements Comparable<PointsImpl> {

    /**
     * Compares this {@code PointsImpl} instance with another for order.
     * Orders {@code PointsImpl} instances in ascending order of their points.
     *
     * @param other The {@code PointsImpl} instance to be compared.
     * @return A negative integer, zero, or a positive integer as this instance
     * has fewer, equal to, or more points than the specified {@code PointsImpl} object.
     */
    @Override
    public int compareTo(final PointsImpl other) {
        return Integer.compare(other.points, points);
    }

    /**
     * Returns a string representation of the {@code PointsImpl} instance.
     *
     * @return A string representation of this {@code PointsImpl} object
     * in the format of "PointsImpl{name='NAME', points=POINTS}".
     */
    @Override
    public String toString() {
        return "PointsImpl{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}

