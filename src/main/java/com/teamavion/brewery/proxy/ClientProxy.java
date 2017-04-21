package com.teamavion.brewery.proxy;


import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.entity.projectile.EntityPotion;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@SuppressWarnings("unused")
public class ClientProxy implements CommonProxy {

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
        RenderingRegistry.registerEntityRenderingHandler(EntityPotion.class,new RenderSnowball(Minecraft.getMinecraft().getRenderManager(),ModItems.potionSplash, Minecraft.getMinecraft().getRenderItem()));
    }

    @Override
    public void postInit() {

    }
}
