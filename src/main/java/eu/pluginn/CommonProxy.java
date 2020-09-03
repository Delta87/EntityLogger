package eu.pluginn;

import eu.pluginn.utils.ModConfig;
import eu.pluginn.utils.ModLogging;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonProxy {

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event){

        if(ModConfig.CONFIG.EnableMod)
        {
            String MobKiller = "";
            String MobName = "";
            String registredEntityName = "";

            if(event.getSource().getTrueSource() instanceof EntityPlayerMP)
            {
                //Placeholder-information
                //registredEntityName = EntityRegistry.getEntry(event.getEntity().getClass()).getRegistryName().toString();



                EntityPlayerMP MPPlayer= (EntityPlayerMP)event.getSource().getTrueSource();
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
    public void onWorldIsLoadet(WorldEvent.Load event){
        if(event.getWorld().provider.getDimension() == 0){
            System.out.println("Overworld is loadet");

            //After overworld is loadet, check registred entites and if they are mobs entities!
            for(ResourceLocation e: ForgeRegistries.ENTITIES.getKeys()){
                if(EntityCreature.class.isAssignableFrom(EntityList.getClass(e)))
                {
                    //ToDo: Add implementation to use a config which mob should be able to track
                    //System.out.println(e.toString() + " is a mob entity");
                }
            }


        }
    }

}
