package eu.pluginn;

import eu.pluginn.utils.ModLogging;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {


    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event){

        String MobKiller = "";
        String MobName = "";

        if(event.getSource().getTrueSource() instanceof EntityPlayerMP)
        {
            EntityPlayerMP MPPlayer= (EntityPlayerMP)event.getSource().getTrueSource();
            MobKiller = MPPlayer.getName();
            MobName = event.getEntity().getName();
            ModLogging.info(String.format("Player %s killed mob %s", MobKiller, MobName));
            MobKiller = "";
            MobName = "";
        }
    }
}
