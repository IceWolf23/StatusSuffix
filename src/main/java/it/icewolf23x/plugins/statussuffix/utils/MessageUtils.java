package it.icewolf23x.plugins.contentcreatorutils.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MessageUtils {
    private final static MiniMessage MiniM = MiniMessage.miniMessage();

    public static Component format(String string) {
        return MiniM.deserialize(colorFormat(string));
    }

    public static String colorFormat(String string) {

        string = string.replace("&", "§")
                .replace("§0", "<black>")
                .replace("§1", "<dark_blue>")
                .replace("§2", "<dark_green>")
                .replace("§3", "<dark_aqua>")
                .replace("§4", "<dark_red>>")
                .replace("§5", "<purple>")
                .replace("§6", "<gold>")
                .replace("§7", "<gray>")
                .replace("§8", "<dark_gray>")
                .replace("§9", "<blue>")
                .replace("§a", "<green>")
                .replace("§b", "<aqua>")
                .replace("§c", "<red>")
                .replace("§d", "<light_purple>")
                .replace("§e", "<yellow>")
                .replace("§f", "<white>")
                .replace("§k", "<obf>")
                .replace("§l", "<b>")
                .replace("§m", "<st>")
                .replace("§n", "<u>")
                .replace("§o", "<i>")
                .replace("§r", "<reset>");

        return string;

    }



}
