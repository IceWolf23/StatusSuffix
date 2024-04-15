package it.icewolf23x.plugins.contentcreatorutils.utils;

import it.icewolf23x.plugins.contentcreatorutils.ContentCreatorUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PermUtils {
    private static final LuckPerms api = ContentCreatorUtils.getLuckPermsApi();

    public static boolean hasPermission(Player player, String permission) {
        assert api != null;
        User user = api.getUserManager().getUser(player.getUniqueId());
        assert user != null;
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }


    public static void setSuffix(Player player, String suffix) {
        assert api != null;
        User user = api.getUserManager().getUser(player.getUniqueId());

        assert user != null;
        user.data().add(SuffixNode.builder(suffix, 100).build());

        api.getUserManager().saveUser(user);
    }

    public static void removeSuffix(Player player, String suffix) {
        assert api != null;
        User user = api.getUserManager().getUser(player.getUniqueId());

        assert user != null;
        if (!(user.getCachedData().getMetaData().getSuffix() == null)) {
            user.data().remove(SuffixNode.builder(Objects.requireNonNull(user.getCachedData().getMetaData().getSuffix()), 100).build());
        }

        api.getUserManager().saveUser(user);
    }

}
