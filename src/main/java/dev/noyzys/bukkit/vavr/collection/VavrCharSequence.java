package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.CharSeq;
import io.vavr.collection.IndexedSeq;
import io.vavr.control.Option;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/* CharSequence :: essentially String wrapper */
public final class VavrCharSequence implements IBukkitCommandExecutor {

    @Override
    public void execute(final Player player, final String[] args) {
        /* generates a progressbar for player  */
        player.sendMessage(createProgressbar(10, 11, '|', ChatColor.RED + "", ChatColor.DARK_PURPLE+ ""));

        final CharSeq flowSequence = CharSeq.of("hujson");
        final Option<Character> foundChar = flowSequence
                .find(character -> character == 'h');
        foundChar.forEach(character -> player.sendMessage("> Found character: " + character));
    }

    /**
     * Creates a progressbar based on the current progress, maximum value,
     * symbol to represent progress, and the colors for both incomplete and
     * complete parts of the progress.
     *
     * @param current the current progress level out of the maximum
     * @param max the maximum value of the progress
     * @param symbol the char used to represent progress on the bar
     * @param colorOne the color code for the incomplete part of the progress
     * @param colorTwo the color code for the complete part of the progress
     * @return representing the progress bar with the given colors
     */
    private String createProgressbar(final int current, final int max,
                                     final char symbol, final String colorOne, final String colorTwo) {
        final int remaining = max - current;

        final IndexedSeq<String> incompleteProgress = CharSeq.fill(remaining, () -> symbol).map(character -> colorOne + character);
        final IndexedSeq<String> completeProgress = CharSeq.fill(current, () -> symbol).map(character -> colorTwo + character);
        return incompleteProgress.mkString() + completeProgress.mkString();
    }

    @Override
    public String getName() {
        return "charsequence";
    }
}






