package com.teamavion.brewery.recipe;

import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by White Autumn on 2017-04-17.
 */
public class ModRecipes {

    public static void register() {
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
        GameRegistry.addRecipe(new ItemStack(ModBlocks.brewery),
                " S ",
                "SCS",
                " S ",
                'S', Blocks.STONE,
                'C', Blocks.CAULDRON);
    }
}
