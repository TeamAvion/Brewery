package com.teamavion.brewery.potion;

import com.teamavion.brewery.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionToxic extends Potion{

    public static ResourceLocation texture;

    public PotionToxic(boolean isBadEffectIn, int liquidColorIn, String name) {
        super(isBadEffectIn, liquidColorIn);
        String path = name.substring(2);
        texture = new ResourceLocation(Reference.MODID, "textures/misc/potionEffect"+path+".png");
        this.setPotionName(name);
        this.setIconIndex(6,0);

    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect)
    {
        return true;
    }
    /*
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon(){return false;}
    */
}