package com.teamavion.brewery.potion;

import com.teamavion.brewery.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by TjKenMate on 4/18/2017.
 */
public class PotionToxic extends Potion{

    public static ResourceLocation texture;

    public PotionToxic(boolean isBadEffectIn, int liquidColorIn, String name) {
        super(isBadEffectIn, liquidColorIn);
        texture = new ResourceLocation(Reference.MODID, "textures/misc/potionEffect"+name+".png");
        this.setPotionName(name);
        this.setIconIndex(0,1);

    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        return super.getStatusIconIndex();
    }

}
