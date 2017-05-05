package org.teamavion.brewery.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class PotionEvents {

    public PotionEvents(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        if(!event.getEntity().getEntityWorld().isRemote) {
        if (event.getEntity() instanceof EntityLivingBase)
            if(((EntityLivingBase) event.getEntity()).getActivePotionEffect(ModPotion.highToxic) != null){
                if(ThreadLocalRandom.current().nextInt(101) < 1) {
                    int randomId;
                    while (true) {
                        randomId = ThreadLocalRandom.current().nextInt(1,28);
                        if (randomId != 7) break;
                    }

                    ((EntityLivingBase) event.getEntity()).addPotionEffect(new PotionEffect(Potion.getPotionById(randomId), 60, 100));
                }
            }
        }
    }
}