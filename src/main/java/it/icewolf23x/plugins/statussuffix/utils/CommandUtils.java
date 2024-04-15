package it.icewolf23x.plugins.contentcreatorutils.utils;


import it.icewolf23x.plugins.contentcreatorutils.ContentCreatorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public class CommandUtils {

    public static int register(String command, Object object) {

        try {
            Objects.requireNonNull(ContentCreatorUtils.PLUGIN.getCommand(command)).setExecutor((CommandExecutor) object);
            BroadUtils.send(false, Bukkit.getConsoleSender(), MessageUtils.format(Objects.requireNonNull(ContentCreatorUtils.CONFIG.getString("messages.commands.register.success")).replace("%command%", command)));
            return 0;
        } catch (Exception e) {
            BroadUtils.send(false, Bukkit.getConsoleSender(), MessageUtils.format((Objects.requireNonNull(ContentCreatorUtils.CONFIG.getString("messages.commands.register.error"))).replace("%command%", command)));
            BroadUtils.send(false, Bukkit.getConsoleSender(), MessageUtils.format(String.format("§c%s", e)));
            return 1;
        }


    }

}
