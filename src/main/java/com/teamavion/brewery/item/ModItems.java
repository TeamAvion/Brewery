package com.teamavion.brewery.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by WhiteAutumn on 2017-04-15.
 */
public class ModItems {

    public static Item potion;
    public static Item bottleSmall;
    public static Item bottleMedium;
    public static Item bottleLarge;

    public static void init(){
        potion = new ItemPotion();
        bottleSmall = new ItemBottle("bottleSmall", "ItemBottleSmall");
        bottleMedium = new ItemBottle("bottleMedium", "ItemBottleMedium");
        bottleLarge = new ItemBottle("bottleLarge", "ItemBottleLarge");
    }

    public static void register() {
        GameRegistry.register(potion);
        GameRegistry.register(bottleSmall);
        GameRegistry.register(bottleMedium);
        GameRegistry.register(bottleLarge);
    }

    public static void registerRenders() {
        registerRender(potion);
        registerRender(bottleSmall);
        registerRender(bottleMedium);
        registerRender(bottleLarge);
    }

    private static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}