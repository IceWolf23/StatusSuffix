package it.icewolf23x.plugins.contentcreatorutils.utils;

import it.icewolf23x.plugins.contentcreatorutils.ContentCreatorUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BroadUtils {
    private static final String PREFIX = ContentCreatorUtils.CONFIG.getString("messages.prefix");

    public static void send(boolean prefix, Player sendTo, Component message) {
        if (prefix) {
            sendTo.sendMessage(MessageUtils.format(PREFIX).append(message));
        } else {
            sendTo.sendMessage(message);
        }
    }


    public static void send(boolean prefix, ConsoleCommandSender sendTo, Component message) {
        if (prefix) {
            sendTo.sendMessage(MessageUtils.format(PREFIX).append(message));
        } else {
            sendTo.sendMessage(message);
        }

    }
}
