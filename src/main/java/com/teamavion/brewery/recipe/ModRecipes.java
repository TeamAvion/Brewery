package com.teamavion.brewery.recipe;

import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

    public static void register() {
        //Darkstone compound
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.darkstoneCompound, 16),
                "COC",
                "OCO",
                "COC",
                'C', "cobblestone",
                'O', Blocks.OBSIDIAN));
        //Darkstone
        GameRegistry.addSmelting(ModItems.darkstoneCompound, new ItemStack(ModBlocks.darkstone), 0F);

        //Small bottle
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleSmall, 3),
                "   ",
                " W ",
                " G ",
                'W', "slabWood",
                'G', "blockGlass"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleSmall, 3),
                " W ",
                " G ",
                "   ",
                'W', "slabWood",
                'G', "blockGlass"));

        //Medium bottle
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleMedium, 2),
                " W ",
                "G G",
                " G ",
                'W', "plankWood",
                'G', "blockGlass"));
        //Large bottle
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleLarge),
                "GLG",
                "G G",
                "GGG",
                'L', "treeWood",
                'G', "blockGlass"));
        //Splash bottle
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleSplash, 4),
                "GWS",
                "P P",
                " P ",
                'W', "plankWood",
                'P', Blocks.GLASS_PANE,
                'G', Items.GUNPOWDER,
                'S', Items.STRING));

        //Brewery
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.brewery),
                "IDI",
                "DCD",
                "IDI",
                'I', "ingotIron",
                'D', ModBlocks.darkstone,
                'C', Items.CAULDRON));
    }
}