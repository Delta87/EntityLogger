package eu.pluginn;

import eu.pluginn.utils.ModConfig;
import eu.pluginn.utils.ModLogging;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import java.io.File;

@Mod(modid = References.MOD_ID, name = References.NAME, version = References.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class Main
{
    private static Logger logger;
    public static String configDir = "";

    @Mod.Instance
    public static Main instance;

    @SidedProxy(serverSide = References.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        configDir = event.getModConfigurationDirectory().getAbsolutePath()+File.separator+References.MOD_ID;
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ModConfig());
        MinecraftForge.EVENT_BUS.register(new CommonProxy());
        ModConfig.setOverworldIsLoadedOnce(false);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        ModLogging.info("Mod loading successfully");
    }

    public static String getGetConfigDir()
    {
        return configDir;
    }
}
