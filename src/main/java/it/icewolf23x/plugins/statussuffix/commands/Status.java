package it.icewolf23x.plugins.statussuffix.commands;

import it.icewolf23x.plugins.statussuffix.StatusSuffix;
import it.icewolf23x.plugins.statussuffix.utils.BroadUtils;
import it.icewolf23x.plugins.statussuffix.utils.MessageUtils;
import it.icewolf23x.plugins.statussuffix.utils.PermUtils;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OnLive implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (PermUtils.hasPermission(player, "contentcreator.command.live")) {

                if (!StatusSuffix.IsStreaming.containsKey(player.getUniqueId())) {
                    StatusSuffix.IsStreaming.put(player.getUniqueId(), false);
                }

                if (!StatusSuffix.IsStreaming.get(player.getUniqueId())) {
                    PermUtils.setSuffix(player, StatusSuffix.CONFIG.getString("messages.commands.live.suffix"));
                    StatusSuffix.IsStreaming.put(player.getUniqueId(), true);
                    BroadUtils.send(true, player, MessageUtils.format(StatusSuffix.CONFIG.getString("messages.commands.live.start")));
                    if (StatusSuffix.CONFIG.getBoolean("messages.commands.live.notify")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p != player) {
                                p.sendActionBar(MessageUtils.format(Objects.requireNonNull(StatusSuffix.CONFIG.getString("messages.commands.live.actionbar-start")).replace("%player%", player.getName())));
                            }
                            p.playSound(Sound.sound(org.bukkit.Sound.valueOf(StatusSuffix.CONFIG.getString("messages.commands.sounds.start")), Sound.Source.MASTER, 1.0f, 1.0f));
                        }
                    }
                    return true;
                }

                if (StatusSuffix.IsStreaming.get(player.getUniqueId())) {
                    PermUtils.removeSuffix(player, StatusSuffix.CONFIG.getString("messages.commands.live.suffix"));
                    StatusSuffix.IsStreaming.put(player.getUniqueId(), false);
                    BroadUtils.send(true, player, MessageUtils.format(StatusSuffix.CONFIG.getString("messages.commands.live.stop")));
                    if (StatusSuffix.CONFIG.getBoolean("messages.commands.live.notify")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p != player) {
                                p.sendActionBar(MessageUtils.format(Objects.requireNonNull(StatusSuffix.CONFIG.getString("messages.commands.live.actionbar-stop")).replace("%player%", player.getName())));
                            }
                            p.playSound(Sound.sound(org.bukkit.Sound.valueOf(StatusSuffix.CONFIG.getString("messages.commands.sounds.stop")), Sound.Source.MASTER, 1.0f, 1.0f));
                        }
                    }
                    return true;
                }
            } else {
                BroadUtils.send(true, player, MessageUtils.format(StatusSuffix.CONFIG.getString("messages.commands.no-permission")));
            }

        } else if (sender instanceof ConsoleCommandSender) {
            BroadUtils.send(true, (ConsoleCommandSender) sender, MessageUtils.format(StatusSuffix.CONFIG.getString("messages.commands.must-be-a-player")));
        }
        return true;
    }
}
