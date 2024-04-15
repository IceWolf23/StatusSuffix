package it.icewolf23x.plugins.contentcreatorutils;

import it.icewolf23x.plugins.contentcreatorutils.commands.OnLive;
import it.icewolf23x.plugins.contentcreatorutils.commands.OnRec;
import it.icewolf23x.plugins.contentcreatorutils.events.PlayerEvents;
import it.icewolf23x.plugins.contentcreatorutils.utils.BroadUtils;
import it.icewolf23x.plugins.contentcreatorutils.utils.CommandUtils;
import it.icewolf23x.plugins.contentcreatorutils.utils.MessageUtils;
import it.icewolf23x.plugins.contentcreatorutils.utils.PermUtils;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class ContentCreatorUtils extends JavaPlugin {

    private int StartupErrors = 0;
    public static ContentCreatorUtils PLUGIN;
    public static FileConfiguration CONFIG;
    public static HashMap<UUID, Boolean> IsRecording = new HashMap<>();
    public static HashMap<UUID, Boolean> IsStreaming = new HashMap<>();

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        PLUGIN = this;
        CONFIG = this.getConfig();



        BroadUtils.send(true, Bukkit.getConsoleSender(), MessageUtils.format(CONFIG.getString("messages.loading.start")));

        StartupErrors += CommandUtils.register("live", new OnLive());
        StartupErrors += CommandUtils.register("rec", new OnRec());
        new PlayerEvents(this);

        if (StartupErrors == 0) {
            BroadUtils.send(true, Bukkit.getConsoleSender(), MessageUtils.format(CONFIG.getString("messages.loading.success")));
        } else if (StartupErrors > 0) {
            BroadUtils.send(true, Bukkit.getConsoleSender(), MessageUtils.format(Objects.requireNonNull(CONFIG.getString("messages.loading.error")).replace("%amount%", String.valueOf(StartupErrors))));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()) {
            PermUtils.removeSuffix(player, CONFIG.getString("messages.commands.live.suffix"));
            PermUtils.removeSuffix(player, CONFIG.getString("messages.commands.rec.suffix"));
        }
    }


    public static LuckPerms getLuckPermsApi() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            return provider.getProvider();
        }
        return null;
    }


}
