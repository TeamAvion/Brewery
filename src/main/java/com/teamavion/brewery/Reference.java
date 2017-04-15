package com.teamavion.brewery;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by TjKenMate on 4/15/2017
 */
public class Reference {

    public static final String MODID = "brewery", VERSION = "0.1a";
    public static final Item[] POTION_ITEMS = {
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
            Items.CAKE,
            Items.GLOWSTONE_DUST,
            Items.FEATHER,
            Items.GOLD_NUGGET,
            Item.getItemFromBlock(Blocks.COBBLESTONE)
    };
}
