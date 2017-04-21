package com.teamavion.brewery.item;

import com.teamavion.brewery.Brewery;
import com.teamavion.brewery.Reference;
import com.teamavion.brewery.entity.projectile.EntityPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static int entityId = 400; //Base entity ID for Brewery

    public static Item darkstoneCompound;
    //public static Item darkstone;

    public static Item bottleSplash;
    public static Item bottleSmall;
    public static Item bottleMedium;
    public static Item bottleLarge;

    public static Item potionSmall;
    public static Item potionMedium;
    public static Item potionLarge;
    public static Item potionSplash;

    public static void init(){
        darkstoneCompound = new ItemBasic("darkstoneCompound", "ItemDarkstoneCompound", 64);

        bottleSplash = new ItemBasic("bottleSplash", "ItemBottleSplash", 64);
        bottleSmall = new ItemBasic("bottleSmall", "ItemBottleSmall", 64);
        bottleMedium = new ItemBasic("bottleMedium", "ItemBottleMedium", 64);
        bottleLarge = new ItemBasic("bottleLarge", "ItemBottleLarge", 64);

        potionSmall = new ItemPotion("potionSmall", "ItemPotionSmall", 2);
        potionMedium = new ItemPotion("potionMedium", "ItemPotionMedium", 4);
        potionLarge = new ItemPotion("potionLarge", "ItemPotionLarge", 6);
        potionSplash = new ItemPotionSplash("potionSplash", "ItemPotionSplash");
    }

    public static void register() {
        GameRegistry.register(darkstoneCompound);

        GameRegistry.register(bottleSplash);
        GameRegistry.register(bottleSmall);
        GameRegistry.register(bottleMedium);
        GameRegistry.register(bottleLarge);

        GameRegistry.register(potionSmall);
        GameRegistry.register(potionMedium);
        GameRegistry.register(potionLarge);
        GameRegistry.register(potionSplash);

        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + "_potion"), EntityPotion.class, "brewery_splashPotion", entityId, Brewery.instance, 64, 1, true);
    }

    public static void registerRenders() {
        registerRender(darkstoneCompound);

        registerRender(bottleSplash);
        registerRender(bottleSmall);
        registerRender(bottleMedium);
        registerRender(bottleLarge);

        registerRender(potionSmall);
        registerRender(potionMedium);
        registerRender(potionLarge);
        registerRender(potionSplash);
        RenderingRegistry.registerEntityRenderingHandler(EntityPotion.class, new RenderSnowball<EntityPotion>(Minecraft.getMinecraft().getRenderManager(), potionSplash, Minecraft.getMinecraft().getRenderItem()));
    }

    private static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}