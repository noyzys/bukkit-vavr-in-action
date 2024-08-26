package dev.noyzys.bukkit.vavr;

import dev.noyzys.bukkit.vavr.listener.EventInvoker;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

final class BukkitVavrListener implements Listener {

    @EventInvoker(PlayerJoinEvent.class)
    void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(">> " + player.getName());
    }
}

