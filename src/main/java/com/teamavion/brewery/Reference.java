package com.teamavion.brewery;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by TjKenMate on 4/15/2017
 */
public class Reference {

    public static final String MODID = "brewery", VERSION = "0.1a";
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
            77,
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
            D = 600*60;
        if(A == 'B')
            D = 400*60;
        if(A == 'C')
            D = 100*6;
        if(A == 'D')
            D = 10*6;
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
            D = 10000*60;
        if(A == 'B')
            D = 6000*60;
        if(A == 'C')
            D = 100*6;
        if(A == 'D')
            D = 10*6;
        return D;
    }
}
