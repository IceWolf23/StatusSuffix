package it.icewolf23x.plugins.contentcreatorutils.utils;

import it.icewolf23x.plugins.contentcreatorutils.ContentCreatorUtils;

import java.io.File;
import java.io.IOException;

public class ConfigUtils {
    private static final String FILE_SUFFIX = ".yml";
    private static File file;

    public static File createFile(String path) {
        file = new File(ContentCreatorUtils.PLUGIN.getDataFolder(), path+FILE_SUFFIX);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }
        return file;
    }

    public static File getFile(String path) {
        file = new File(ContentCreatorUtils.PLUGIN.getDataFolder(), path+FILE_SUFFIX);
        return file;
    }

    public static boolean fileExists(String path) {
        file = new File(ContentCreatorUtils.PLUGIN.getDataFolder(), path+FILE_SUFFIX);
        return file.exists();
    }

}
