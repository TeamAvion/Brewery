package org.teamavion.brewery;

import org.teamavion.brewery.block.ModBlocks;
import org.teamavion.brewery.item.ModItems;
import org.teamavion.brewery.potion.ModPotion;
import org.teamavion.brewery.potion.PotionEvents;
import org.teamavion.brewery.recipe.ModRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.teamavion.brewery.proxy.*;

@Mod(modid = Reference.MODID, name="Brewery", version = Reference.VERSION, dependencies = "required-before:guideapi")
public class Brewery {

    @SidedProxy(clientSide = "org.teamavion.brewery.proxy.ClientProxy", serverSide = "org.teamavion.brewery.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value = Reference.MODID)
    public static Brewery instance;

    @EventHandler
    public void preInt(FMLPreInitializationEvent e){
        ModBlocks.init();
        ModItems.init();
        ModPotion.init();
        ModBlocks.register();
        ModItems.register();
        ModRecipes.register();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init();
        new PotionEvents();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit();
    }
}
