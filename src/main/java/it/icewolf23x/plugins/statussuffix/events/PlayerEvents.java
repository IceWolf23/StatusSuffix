package it.icewolf23x.plugins.contentcreatorutils.events;

import it.icewolf23x.plugins.contentcreatorutils.ContentCreatorUtils;
import it.icewolf23x.plugins.contentcreatorutils.utils.PermUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {
    public PlayerEvents(ContentCreatorUtils plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        try {
            PermUtils.removeSuffix(event.getPlayer(), ContentCreatorUtils.CONFIG.getString("messages.commands.rec.suffix"));
        } catch (NullPointerException ignored) {}
        try {
            PermUtils.removeSuffix(event.getPlayer(), ContentCreatorUtils.CONFIG.getString("messages.commands.live.suffix"));
        } catch (NullPointerException ignored) {}
        ContentCreatorUtils.IsRecording.put(event.getPlayer().getUniqueId(), false);
        ContentCreatorUtils.IsStreaming.put(event.getPlayer().getUniqueId(), false);
    }

}
