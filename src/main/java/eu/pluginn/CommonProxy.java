package eu.pluginn;

import eu.pluginn.utils.ModConfig;
import eu.pluginn.utils.ModLogging;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CommonProxy {

    private static boolean writeIntoFile = false;

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {

        if (ModConfig.CONFIG.EnableMod) {
            String MobKiller = "";
            String MobName = "";
            String registredEntityName = "";

            if (event.getSource().getTrueSource() instanceof EntityPlayerMP) {
                //Placeholder-information
                //registredEntityName = EntityRegistry.getEntry(event.getEntity().getClass()).getRegistryName().toString();

                EntityPlayerMP MPPlayer = (EntityPlayerMP) event.getSource().getTrueSource();
                MobKiller = MPPlayer.getName();
                MobName = event.getEntity().getName();
                ModLogging.info(String.format("Player %s killed mob %s", MobKiller, MobName));
                MobKiller = "";
                MobName = "";

                //Placeholder-information
                //System.out.println("TypeName?: "+registredEntityName);


            }
        }
    }

    @SubscribeEvent
    public void onWorldIsLoadet(WorldEvent.Load event) {
        if (event.getWorld().provider.getDimension() == 0) {
            System.out.println("Overworld is loadet");

            //create entity list only once after start and if the list is not already created
            //if you set it to false, the list will generated after every load
            if (!ModConfig.isOverworldIsLoadedOnce()) {
                File yourFile = new File(Main.getGetConfigDir() + "/available-mob-entities-on-this-server.txt");
                FileOutputStream oFile = null;

                if (ModConfig.CONFIG.LoadEntityListOnlyOnce) {
                    ModConfig.setOverworldIsLoadedOnce(true);
                }

                if (!yourFile.exists()) {
                    writeIntoFile = true;
                    try {
                        yourFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("CONFIG: " + Main.getGetConfigDir());

                if (writeIntoFile) {
                    try {
                        oFile = new FileOutputStream(yourFile, true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    //After overworld is loadet, check registred entites and if they are mob entities!
                    for (ResourceLocation e : ForgeRegistries.ENTITIES.getKeys()) {
                        if (EntityLiving.class.isAssignableFrom(EntityList.getClass(e))) {
                            if (yourFile.exists()) {
                                try {
                                    oFile.write(String.format("%s\n", e.toString()).getBytes());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }

                    if(writeIntoFile == true){
                        writeIntoFile = false;
                        try {
                            oFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
