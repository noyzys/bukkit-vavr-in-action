package dev.noyzys.bukkit.vavr.collection;

import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.collection.Tree;
import org.bukkit.entity.Player;

/* Tree :: A general Tree */
public final class VavrTree implements IBukkitCommandExecutor {

    /**
     * This method is the entry point of the program where the command tree is constructed and
     * displayed to the player.
     *
     * @param args Command line arguments.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Tree<String> commandTree = Tree.of("Admin",
                Tree.of("AdminCommands", Tree.of("ban"), Tree.of("sudo")),
                Tree.of("PlauerCpmmands", Tree.of("help")));

        player.sendMessage("> Command Tree#of struct: " + commandTree);
        displayCommandsTree(player, commandTree, 0);

    }

    /**
     * Recursively displays the commands tree by sending indented messages to the player.
     *
     * @param player The player to whom the command tree will be displayed.
     * @param tree The command tree to display.
     * @param depth The depth of the current node within the tree, used for indentation.
     */
    private void displayCommandsTree(final Player player, final Tree<String> tree, final int depth) {
        final String indent = " ".repeat(depth);
        player.sendMessage(indent + tree.getValue());

        tree.getChildren().forEach(child -> displayCommandsTree(player, child, depth + 1));
    }


    @Override
    public String getName() {
        return "tree";
    }
}
