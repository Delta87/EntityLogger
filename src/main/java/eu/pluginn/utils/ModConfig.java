package eu.pluginn.utils;

import eu.pluginn.References;
import net.minecraftforge.common.config.Config;

public class ModConfig {

    private static boolean overworldIsLoadedOnce = false;

    public static boolean isOverworldIsLoadedOnce() {
        return overworldIsLoadedOnce;
    }

    public static void setOverworldIsLoadedOnce(boolean overworldIsLoadedOnce) {
        ModConfig.overworldIsLoadedOnce = overworldIsLoadedOnce;
    }



    @Config(modid = References.MOD_ID, type = Config.Type.INSTANCE, name = References.MOD_ID+"/"+References.MOD_ID, category = "general")
    public static class CONFIG
    {
        @Config.Name("EnableMod") // Enable or disable the loggingfeature
        public static Boolean EnableMod = true;

        @Config.Name("LoadEntityListOnlyOnce") // load the entitylist only once
        public static Boolean LoadEntityListOnlyOnce = true;


        @Config.Name("LogType") //LogType
        public static int LogType = 0;

    }

    @Config(modid = References.MOD_ID, type = Config.Type.INSTANCE, name = References.MOD_ID+"/"+References.MOD_ID, category = "mysql")
    public static class CONFIG_MYSQL {

        @Config.Name("Mysqlhost")
        public static String MySQLHost = "localhost";

        @Config.Name("Mysqlusername")
        public static String MySQLName = "";

        @Config.Name("Mysqlpassword")
        public static String MySQLPass = "";

        @Config.Name("Mysqldatabase")
        public static String MySQLBase = "";

        @Config.Name("Mysqlport")
        public static int MySQLPort = 3306;
    }

}
