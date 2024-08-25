package dev.noyzys.bukkit.vavr;

import dev.noyzys.bukkit.vavr.collection.*;
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
                new VavrIterator(this),
                new VavrMapper(),
                new VavrOrdered(),
                new VavrSequence(this),
                new VavrArray(),
                new VavrQueue()
        );

        commands.forEach(command -> getCommand(command.getName()).setExecutor(command));
    }
}
