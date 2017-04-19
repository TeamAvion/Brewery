package com.teamavion.brewery;

import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import com.teamavion.brewery.potion.ModPotions;
import com.teamavion.brewery.recipe.ModRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.teamavion.brewery.proxy.*;

/**
 * Created by TjKenMate on 4/15/2017XD
 */

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class Brewery {

    @SidedProxy(clientSide = "com.teamavion.brewery.proxy.ClientProxy", serverSide = "com.teamavion.brewery.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInt(FMLPreInitializationEvent e){
        ModBlocks.init();
        ModItems.init();
        ModPotions.init();
        ModBlocks.register();
        ModItems.register();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        ModRecipes.register();
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit();
    }
}
