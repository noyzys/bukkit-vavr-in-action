package dev.noyzys.bukkit.vavr.concurrent;

import dev.noyzys.bukkit.vavr.BukkitVavrPlugin;
import dev.noyzys.bukkit.vavr.IBukkitCommandExecutor;
import io.vavr.concurrent.Promise;
import org.bukkit.entity.Player;

/* Promise :: A general promise of async task */
public final class VavrPromise implements IBukkitCommandExecutor {

    private final BukkitVavrPlugin vavrPlugin;

    public VavrPromise(final BukkitVavrPlugin vavrPlugin) {
        this.vavrPlugin = vavrPlugin;
    }

    /**
     * Initiates an asynchronous task that completes a promise with a specific result.
     */
    @Override
    public void execute(final Player player, final String[] args) {
        final Promise<String> stringPromise = Promise.make();

        vavrPlugin.getServer().getScheduler().runTaskAsynchronously(vavrPlugin, () -> {
            final String result = ";)";
            stringPromise.success(result);
        });
    }

    @Override
    public String getName() {
        return "promise";
    }
}