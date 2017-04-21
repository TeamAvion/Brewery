package com.teamavion.brewery.potion;

import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.tile.TileBrewery;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.concurrent.ThreadLocalRandom;

public class CustomPotionHandler {

    public static void addToxicity(EntityLivingBase entityLiving) {
        //If player has effect high toxic, apply random negative effect or do nothing
        if(entityLiving.getActivePotionEffect(ModPotion.highToxic) != null){
            int result = ThreadLocalRandom.current().nextInt(6);
            switch (result) {
                case 0:
                    entityLiving.setHealth(0.5F);
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(20),600*5, 100));
                    break;
                case 1:
                    entityLiving.setHealth(5.0F);
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(9),600*10));
                    break;
                case 3:
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(9),600*5));
                    break;
                case 2:
                    break;
                case 4:
                    break;
                case 5:
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(9),600*10));
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(20),600, 10));
                    break;
            }
        }

        if(entityLiving.getActivePotionEffect(ModPotion.lowToxic) != null && entityLiving.getActivePotionEffect(ModPotion.lowToxic).getAmplifier() > 0) {
            entityLiving.addPotionEffect(new PotionEffect(ModPotion.lowToxic, 600 * 10, 1));
            entityLiving.addPotionEffect(new PotionEffect(ModPotion.highToxic, 600 * 6));
        } else if(entityLiving.getActivePotionEffect(ModPotion.lowToxic) != null)
            entityLiving.addPotionEffect(new PotionEffect(ModPotion.lowToxic, 600*4, 1));
        else
            entityLiving.addPotionEffect(new PotionEffect(ModPotion.lowToxic, 600*1));
    }

    public static void addPotionEffectFromNBT(NBTTagCompound compound, EntityLivingBase entityLiving) {
        if(compound != null) {
            for(int i = 0; i < TileBrewery.CAPACITY; i++) {
                if (compound.hasKey("potion_ID_"+i, 99)) {
                    int id = compound.getInteger("potion_ID_" + i);
                    char grade = (char) compound.getShort("potion_grade_" + i);
                    if(Reference.EFFECTS_SCALABLE[compound.getInteger("potion_ID_"+i)])
                        entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeScalable(grade), Reference.amplification(grade)));
                    else
                        entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeNotScalable(grade)));
                }
            }
        }
    }



}