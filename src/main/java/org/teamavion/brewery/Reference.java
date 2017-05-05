package org.teamavion.brewery;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.teamavion.brewery.item.ModItems;

public class Reference {

    public static final String MODID = "brewery", VERSION = "1.1.0dev";
    public static final CreativeTabs BREWERY_TAB = new CreativeTabs("brewery") { @Override public ItemStack getTabIconItem() { return new ItemStack(ModItems.potionLarge); } };
    public static final Ingredient[] INGREDIENT_LIST = { //TODO: Replace with a invert effect system? //TODO: Add max scale system?
            /*0: NULL*/             null,
            /*1: SPEED*/            new Ingredient(Items.SUGAR,                             true,   52,     (int)(1.5*60*20)),
            /*2: SLOWNESS*/         new Ingredient(Item.getItemFromBlock(Blocks.SOUL_SAND), true,   72,     (int)(1.5*60*20)),
            /*3: HASTE*/            new Ingredient(Items.EMERALD,                           true,   49,     (int)(1.5*60*20)),
            /*4: MINING FATIGUE*/   new Ingredient(Item.getItemFromBlock(Blocks.PACKED_ICE),true,   82,     (int)(1.5*60*20)),
            /*5: STRENGTH*/         new Ingredient(Items.QUARTZ,                            true,   74,     (int)(1.5*60*20)),
            /*6: INSTANT HEALTH*/   new Ingredient(Items.GOLDEN_APPLE,                      true,   73,     (int)(1.5*60*20)),
            /*7: INSTANT DAMAGE*/   new Ingredient(Items.ARROW,                             true,   77,     (int)(1.5*60*20)),
            /*8: JUMP BOOST*/       new Ingredient(Items.CARROT,                            true,   53,     (int)(1.5*60*20)),
            /*9: NAUSEA*/           new Ingredient(Items.NETHER_WART,                       false,  40,     (int)(1.5*60*20)),
            /*10: REGENERATION*/    new Ingredient(Items.GOLD_INGOT,                        true,   74,     (int)(1.5*60*20)),
            /*11: RESISTANCE*/      new Ingredient(Item.getItemFromBlock(Blocks.OBSIDIAN),  true,   90,     (int)(1.5*60*20)),
            /*12: FIRE RESISTANCE*/ new Ingredient(Items.BLAZE_POWDER,                      false,  100,    (int)(1.5*60*20)),
            /*13: WATER BREATHING*/ new Ingredient(Items.PRISMARINE_SHARD,                  false,  24,     (int)(1.5*60*20)),
            /*14: INVISIBILITY*/    new Ingredient(Items.GHAST_TEAR,                        false,  60,     (int)(1.5*60*20)),
            /*15: BLINDNESS*/       new Ingredient(Items.COAL,                              false,  100,    (int)(1.5*60*20)),
            /*16: NIGHT VISION*/    new Ingredient(Items.GOLDEN_CARROT,                     false,  56,     (int)(1.5*60*20)),
            /*17: HUNGER*/          new Ingredient(Items.ROTTEN_FLESH,                      true,   66,     (int)(1.5*60*20)),
            /*18: WEAKNESS*/        new Ingredient(Items.BONE,                              true,   84,     (int)(1.5*60*20)), //TODO: REPLACE ITEM
            /*19: POISON*/          new Ingredient(Items.SPIDER_EYE,                        true,   40,     (int)(1.5*60*20)),
            /*20: WITHER*/          new Ingredient(Items.SKULL,                             true,   88,     (int)(1.5*60*20)), //TODO: REPLACE ITEM
            /*21: HEALTH BOOST*/    new Ingredient(Items.APPLE,                             true,   30,     (int)(1.5*60*20)),
            /*22: ABSORPTION*/      new Ingredient(Items.LEATHER,                           true,   81,     (int)(1.5*60*20)),
            /*23: SATURATION*/      new Ingredient(Items.CAKE,                              true,   41,     (int)(1.5*60*20)),
            /*24: GLOWING*/         new Ingredient(Items.GLOWSTONE_DUST,                    false,  100,    (int)(1.5*60*20)),
            /*25: LEVITATION*/      new Ingredient(Items.FEATHER,                           true,   59,     (int)(1.5*60*20)),
            /*26: LUCK*/            new Ingredient(Items.GOLD_NUGGET,                       true,   69,     (int)(1.5*60*20)),
            /*27: BAD LUCK*/        new Ingredient(Item.getItemFromBlock(Blocks.COBBLESTONE),true,   66,     (int)(1.5*60*20))
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
            true,//SUGAR
            true,//SOUL SAND
            true,//EMERALD
            true,//ICE
            true,//QUARTZ
            true,//GOLDEN APPLE
            true,//ARROW
            true,//CARROT
            false,//NETHER WART
            true,//GOLDEN INGOT
            true,//OBSIDIAN
            false,//BLAZE
            false,//PRIS SHARD
            false,//TEAR
            false,//COAL
            false,//GOLDEN CARROT
            true,//ROTTEN FLESH
            true,//BONE
            true,//SPIDER EYE
            true,//SKULL
            true,//APPLE
            true,//LEATHER
            true,//CAKE
            false,//GLOWSTONE
            false,//FEATHER
            true,
            true,
    };

    public static final int[] EFFECT_TEMPRETURES = {
            -100,
            52,
            77,//HERE
            49,//EMERALD
            82,//ICE
            74,//QUARTZ
            73,//GOLDEN APPLE
            77,//ARROW
            53,//CARROT
            40,//NETHER WART
            74,//GOLDEN INGOT
            76,//OBSIDIAN
            100,//BLAZE
            24,//PRIS SHARD
            60,//TEAR
            100,//COAL
            56,//GOLDEN CARROT
            66,//ROTTEN FLESH
            84,//BONE
            40,//SPIDER EYE
            88,//SKULL
            71,//APPLE
            72,//LEATHER
            78,//CAKE
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