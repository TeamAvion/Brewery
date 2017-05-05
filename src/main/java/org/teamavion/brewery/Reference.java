package org.teamavion.brewery;

import org.teamavion.brewery.block.tile.TileBrewery;
import org.teamavion.brewery.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Reference {

    public static final String MODID = "brewery", VERSION = "1.0";
    public static final CreativeTabs tabBrewery = new CreativeTabs("brewery") { @Override public ItemStack getTabIconItem() { return new ItemStack(ModItems.potionLarge); } };

    public static final Ingredient[] INGREDIENT_LIST = {
            null,
            new Ingredient(Items.SUGAR, true, 52, (int)(1.5*60*20)),
            new Ingredient(Item.getItemFromBlock(Blocks.SOUL_SAND), true, 72, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),
            new Ingredient(Items.SUGAR, false, 52, (int)(1.5*60*20)),

    };


    public static final Item[] EFFECT_ITEMS = {
            null,
            Items.SUGAR,
            Item.getItemFromBlock(Blocks.SOUL_SAND),
            Items.EMERALD,
            Item.getItemFromBlock(Blocks.PACKED_ICE),
            Items.QUARTZ,
            Items.GOLDEN_APPLE,
            Items.ARROW,
            Items.CARROT,
            Items.NETHER_WART,
            Items.GOLD_INGOT,
            Item.getItemFromBlock(Blocks.OBSIDIAN),
            Items.BLAZE_POWDER,
            Items.PRISMARINE_SHARD,
            Items.GHAST_TEAR,
            Items.COAL,
            Items.GOLDEN_CARROT,
            Items.ROTTEN_FLESH,
            //TEMPORARY ITEM
            Items.BONE,
            Items.SPIDER_EYE,
            Items.SKULL,
            Items.APPLE,
            Items.LEATHER,
            Items.CAKE,
            Items.GLOWSTONE_DUST,
            Items.FEATHER,
            Items.GOLD_NUGGET,
            Item.getItemFromBlock(Blocks.COBBLESTONE)
    };
    public static final boolean[] EFFECTS_SCALABLE = {
            false,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            false,
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            true,
            true,
    };

    public static final int[] EFFECT_TEMPRETURES = {
            -100,
            52,
            77,//HERE
            49,
            82,
            74,
            73,
            77,
            53,
            40,
            74,
            76,
            100,
            24,
            60,
            100,
            56,
            66,
            84,
            40,
            88,
            71,
            72,
            78,
            100,
            59,
            69,
            66
    };

    public static final int[] EFFECT_TIMES = {
            //TODO: Implement time that not 1.5 min on all
            //x*20*60 (converts x from minuets to ticks)
            -100,
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20),
            (int)(1.5*60*20)
    };

    public static int durationFromGradeNotScalable(char A){
        int D = 1;
        if(A == 'A')
            D = 18000/2;
        if(A == 'B')
            D = 12000/2;
        if(A == 'C')
            D = 3600/2;
        if(A == 'D')
            D = 600/2;
        return D;
    }

    public static int amplification(char A) {
        int D = 1;
        if(A == 'A')
            D = 20;
        if(A == 'B')
            D = 7;
        if(A == 'C')
            D = 2;
        if(A == 'D')
            D = 1;
        return D;
    }

    public static int durationFromGradeScalable(char A) {
        int D = 1;
        if(A == 'A')
            D = 18000;
        if(A == 'B')
            D = 12000;
        if(A == 'C')
            D = 3600/2;
        if(A == 'D')
            D = 600;
        return D;
    }

    private static class Ingredient {
        Item item;
        boolean isScalable;
        int idealTemperature;
        int effectTime;

        Ingredient(Item item, boolean isScalable, int idealTemperature, int effectTime) {
            this.item = item;
            this.isScalable = isScalable;
            this.idealTemperature = idealTemperature;
            this.effectTime = effectTime;
        }
    }
}