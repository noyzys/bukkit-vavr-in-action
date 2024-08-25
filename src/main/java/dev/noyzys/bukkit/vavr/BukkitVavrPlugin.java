package dev.noyzys.bukkit.vavr;

import dev.noyzys.bukkit.vavr.collection.VavrCharSequence;
import dev.noyzys.bukkit.vavr.collection.VavrIndexedSequence;
import dev.noyzys.bukkit.vavr.collection.VavrIterator;
import dev.noyzys.bukkit.vavr.collection.VavrLinearSequence;
import io.vavr.collection.List;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitVavrPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // register command logic
        final List<IBukkitCommandExecutor> commands = List.of(
                new VavrCharSequence(),
                new VavrIndexedSequence(this),
                new VavrLinearSequence(),
                new VavrIterator(this)
        );

        commands.forEach(command -> getCommand(command.getName()).setExecutor(command));
    }
}
