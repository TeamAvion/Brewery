package com.teamavion.brewery.potion;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by TjKenMate on 4/18/2017.
 */
public class ModPotion {

    public static Potion lowToxic;
    public static Potion highToxic;

    public static void init(){
        lowToxic = registerPotion(ChatFormatting.DARK_GREEN + "Contaminated", new ResourceLocation("contaminated"), true, 0x000000);
        highToxic = registerPotion(ChatFormatting.GREEN + "Toxic", new ResourceLocation("Toxic"), true, 0x09f725);
    }

    private static Potion registerPotion(String name, ResourceLocation location, boolean badEffect, int potionColour)
    {
        Potion potion = new PotionToxic(badEffect, potionColour, name);
        GameRegistry.register(potion.setRegistryName(location.getResourcePath()));
        return potion;
    }
}