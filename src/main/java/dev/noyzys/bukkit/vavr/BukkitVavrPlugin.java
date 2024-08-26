package dev.noyzys.bukkit.vavr;

import dev.noyzys.bukkit.vavr.collection.VavrArray;
import dev.noyzys.bukkit.vavr.collection.VavrCharSequence;
import dev.noyzys.bukkit.vavr.collection.VavrIndexedSequence;
import dev.noyzys.bukkit.vavr.collection.VavrIterator;
import dev.noyzys.bukkit.vavr.collection.VavrLinearSequence;
import dev.noyzys.bukkit.vavr.collection.VavrMapper;
import dev.noyzys.bukkit.vavr.collection.VavrOrdered;
import dev.noyzys.bukkit.vavr.collection.VavrQueue;
import dev.noyzys.bukkit.vavr.collection.VavrSequence;
import dev.noyzys.bukkit.vavr.collection.VavrStream;
import dev.noyzys.bukkit.vavr.collection.VavrTree;
import dev.noyzys.bukkit.vavr.collection.VavrVector;
import dev.noyzys.bukkit.vavr.concurrent.VavrFuture;
import dev.noyzys.bukkit.vavr.concurrent.VavrPromise;
import dev.noyzys.bukkit.vavr.control.VavrEither;
import dev.noyzys.bukkit.vavr.control.VavrOption;
import dev.noyzys.bukkit.vavr.control.VavrTry;
import dev.noyzys.bukkit.vavr.control.VavrValidation;
import dev.noyzys.bukkit.vavr.listener.EventListenerRegistrar;
import dev.noyzys.bukkit.vavr.struct.VavrMatch;
import dev.noyzys.bukkit.vavr.struct.VavrTuplet;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
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
                new VavrQueue(),
                new VavrStream(this),
                new VavrTree(),
                new VavrVector(),
                new VavrMatch(),
                new VavrTuplet(),
                new VavrFuture(this),
                new VavrPromise(this),
                new VavrEither(this),
                new VavrOption(),
                new VavrTry(this),
                new VavrValidation()
        );

        commands.forEach(command -> Option.of(getCommand(command.getName()))
                        .peek(cmd -> cmd.setExecutor(command)));

        // register events logic
        final EventListenerRegistrar listenerRegistrar = new EventListenerRegistrar(this);
        Try.run(() -> listenerRegistrar.subscribeToEvents(this, new BukkitVavrListener()))
                .onFailure(ex -> getLogger().severe("> Could not register events: " + ex.getMessage()));;
    }
}
