package com.teamavion.brewery.recipe;

import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by White Autumn on 2017-04-17.
 */
public class ModRecipes {

    public static void register() {
        //Darkstone compound
        GameRegistry.addRecipe(new ItemStack(ModItems.darkstoneCompound, 4),
                "COC",
                "OCO",
                "COC",
                'C', Blocks.COBBLESTONE,
                'O', Blocks.OBSIDIAN);
        //Darkstone
        GameRegistry.addSmelting(ModItems.darkstoneCompound, new ItemStack(ModItems.darkstone), 0F);

        //Small bottle
        GameRegistry.addRecipe(new ItemStack(ModItems.bottleSmall, 3),
                "   ",
                " W ",
                " G ",
                'W', Blocks.WOODEN_SLAB,
                'G', Blocks.GLASS);
        GameRegistry.addRecipe(new ItemStack(ModItems.bottleSmall, 3),
                " W ",
                " G ",
                "   ",
                'W', Blocks.WOODEN_SLAB,
                'G', Blocks.GLASS);

        //Medium bottle
        GameRegistry.addRecipe(new ItemStack(ModItems.bottleMedium, 2),
                " W ",
                "G G",
                " G ",
                'W', Blocks.PLANKS,
                'G', Blocks.GLASS);

        //Large bottle
        GameRegistry.addRecipe(new ItemStack(ModItems.bottleLarge),
                "GLG",
                "G G",
                "GGG",
                'L', Blocks.LOG,
                'G', Blocks.GLASS);
        //Splash bottle
        GameRegistry.addRecipe(new ItemStack(ModItems.bottleSplash, 4),
                "GWS",
                "G G",
                " G ",
                'W', Blocks.PLANKS,
                'G', Blocks.GLASS,
                'G', Items.GUNPOWDER,
                'S', Items.STRING);

        //Brewery
        GameRegistry.addRecipe(new ItemStack(ModBlocks.brewery),
                "IDI",
                "DCD",
                "IDI",
                'I', Items.IRON_INGOT,
                'D', ModItems.darkstone,
                'C', Items.CAULDRON);
    }
}
