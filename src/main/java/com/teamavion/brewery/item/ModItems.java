package com.teamavion.brewery.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static Item potionSmall;
    public static Item potionMedium;
    public static Item potionLarge;
    public static Item bottleSmall;
    public static Item bottleMedium;
    public static Item bottleLarge;

    public static void init(){
        potionSmall = new ItemPotion("potionSmall", "ItemPotionSmall", 2);
        potionMedium = new ItemPotion("potionMedium", "ItemPotionMedium", 4);
        potionLarge = new ItemPotion("potionLarge", "ItemPotionLarge", 6);
        bottleSmall = new ItemBasic("bottleSmall", "ItemBottleSmall", 64);
        bottleMedium = new ItemBasic("bottleMedium", "ItemBottleMedium", 64);
        bottleLarge = new ItemBasic("bottleLarge", "ItemBottleLarge", 64);
    }

    public static void register() {
        GameRegistry.register(potionSmall);
        GameRegistry.register(potionMedium);
        GameRegistry.register(potionLarge);
        GameRegistry.register(bottleSmall);
        GameRegistry.register(bottleMedium);
        GameRegistry.register(bottleLarge);
    }

    public static void registerRenders() {
        registerRender(potionSmall);
        registerRender(potionMedium);
        registerRender(potionLarge);
        registerRender(bottleSmall);
        registerRender(bottleMedium);
        registerRender(bottleLarge);
    }

    private static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}