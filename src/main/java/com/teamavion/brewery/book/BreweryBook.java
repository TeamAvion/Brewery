package com.teamavion.brewery.book;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.Entry;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.*;
import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by TjKenMate on 4/19/2017.
 */
@GuideBook
public class BreweryBook implements IGuideBook {

    public static Book breweryGuide;

    /**
     * Build your guide book here. The returned book will be registered for you. The book created here can be modified
     * later, so make sure to keep a reference for yourself.
     *
     * @return a built book to be registered.
     */
    @Nullable
    @Override
    public Book buildBook() {
        Map<ResourceLocation, EntryAbstract> cauldron = new LinkedHashMap<ResourceLocation, EntryAbstract>();
        Map<ResourceLocation, EntryAbstract> ingredients = new LinkedHashMap<ResourceLocation, EntryAbstract>();
        Map<ResourceLocation, EntryAbstract> toxicity = new LinkedHashMap<ResourceLocation, EntryAbstract>();

        /**
         * Adding Brewing Pages
         */
        List<IPage> BrewingPage = new ArrayList<IPage>();
        BrewingPage.add(new PageItemStack("To do any brewing you need a " + I18n.translateToLocal("brewery.category.cauldron.cauldron.name"), new ItemStack(ModBlocks.brewery)));
        BrewingPage.add(new PageIRecipe(new ShapedOreRecipe(new ItemStack(ModItems.darkstoneCompound, 16),
                "COC",
                "OCO",
                "COC",
                'C', "cobblestone",
                'O', Blocks.OBSIDIAN)));
        BrewingPage.add(new PageFurnaceRecipe(ModItems.darkstoneCompound));
        BrewingPage.add(new PageIRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.brewery),
                "IDI",
                "DCD",
                "IDI",
                'I', "ingotIron",
                'D', ModBlocks.darkstone,
                'C', Items.CAULDRON)));
        BrewingPage.add(new PageText("Now that you have a " + I18n.translateToLocal("brewery.category.cauldron.cauldron.name")+ " can start brewing!" +
                "\n\nBrewing is simple and comes in 3 steps:" +
                "\nPut in water" +
                "\n\nPut in your ingredients and heat it up" +
                "\n\nScoop it into a Magical container"));
        BrewingPage.add(new PageText(TextFormatting.BOLD +"Adding Water Part I" + TextFormatting.WHITE +
                "\n\nTo add water just right click with a bucket!\n\n" +
                "Each bucket of water is equal to 1000 liquid in the cauldron.\nYou can have max 3000 liquid in the "+  I18n.translateToLocal("brewery.category.cauldron.cauldron.name")+ " at a time"));
        BrewingPage.add(new PageText(TextFormatting.BOLD + "Adding Water Part II" + TextFormatting.WHITE + "\n\nNote: " + TextFormatting.BOLD + TextFormatting.DARK_RED +"ONCE YOU ADD INGREDENTS YOU CANT ADD ANYMORE WATER"));
        BrewingPage.add(new PageText(TextFormatting.BOLD + "Adding Ingredients Part I"+ TextFormatting.WHITE +
                "\n\nTo add an ingredient just right click the " + I18n.translateToLocal("brewery.category.cauldron.cauldron.name") +" with an ingredient\n\n" +
                "The more of the same ingredient you add and the closer you are to the ideal temperature, the more potent the effect will be!"));
        BrewingPage.add(new PageText(TextFormatting.BOLD + "Adding Ingredients Part II" + TextFormatting.WHITE +
                "\n\nIngredients can be found in the ingredients tab" +
                "\n\nYou can have max of 9 ingredients in the potion so chose wisely"));
        BrewingPage.add(new PageText(TextFormatting.BOLD + "Scoop it into a Magical container" + TextFormatting.WHITE +
                "\n\nScoop the contents of the potions into a Magical container" +
                "\n\nOnce the "+ I18n.translateToLocal("brewery.category.cauldron.cauldron.name") + " has reached 0, it will reset itself and you can start all over again!"));
        cauldron.put(new ResourceLocation(Reference.MODID, "CEntry0"), new EntryItemStack(BrewingPage, "How To Brew!", new ItemStack(ModBlocks.brewery)));

        List<IPage> BottlePage = new ArrayList<IPage>();
        BottlePage.add(new PageText("To get potions from the brewery you need to use a magical container.\n\nDrinkable containers come in three sizes each holding 1000, 2000, 3000 liquid respectively, and " + I18n.translateToLocal("item.bottleSplash.name") + " comes in one size only holding 1000 liquid"));
        BottlePage.add(new PageText("The three types of drinkable container are in order of largest to smallest:\n" +I18n.translateToLocal("item.bottleLarge.name") + ", "+ I18n.translateToLocal("item.bottleMedium.name")+ ", " + I18n.translateToLocal("item.bottleSmall.name") + ", "));
        BottlePage.add(new PageItemStack(I18n.translateToLocal("item.bottleLarge.name") + ":\nHolds 6 Swigs requires 3000 Liquid to fill", ModItems.bottleLarge));
        BottlePage.add(new PageItemStack(I18n.translateToLocal("item.bottleMedium.name") + ":\nHolds 4 Swigs requires 2000 Liquid to fill", ModItems.bottleMedium));
        BottlePage.add(new PageItemStack(I18n.translateToLocal("item.bottleSmall.name") + ":\nHolds 2 Swigs requires 1000 Liquid to fill", ModItems.bottleSmall));
        BottlePage.add(new PageItemStack(I18n.translateToLocal("item.bottleSplash.name") + ":\nOne use requires 1000 Liquid to fill", ModItems.bottleSplash));
        BottlePage.add(new PageIRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleSmall, 3),
                " W ",
                " G ",
                "   ",
                'W', Blocks.WOODEN_SLAB,
                'G', Blocks.GLASS)));
        BottlePage.add(new PageIRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleMedium, 2),
                " W ",
                "G G",
                " G ",
                'W', "plankWood",
                'G', "blockGlass")));
        BottlePage.add(new PageIRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleLarge),
                "GLG",
                "G G",
                "GGG",
                'L', "logWood",
                'G', "blockGlass")));
        BottlePage.add(new PageIRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bottleSplash, 4),
                "GWS",
                "P P",
                " P ",
                'W', "plankWood",
                'P', Blocks.GLASS_PANE,
                'G', Items.GUNPOWDER,
                'S', Items.STRING)));
        cauldron.put(new ResourceLocation(Reference.MODID, "CEntry1"), new EntryItemStack(BottlePage, "Magical Containers", new ItemStack(ModItems.bottleLarge)));

        List<IPage> TemperaturePage = new ArrayList<IPage>();
        TemperaturePage.add(new PageText("To heat up the " + I18n.translateToLocal("brewery.category.cauldron.cauldron.name") + " you need to place a fire either one or two blocks below it with water in it" +
                "\n\nNote: " + I18n.translateToLocal("brewery.category.cauldron.blockinway")));
        TemperaturePage.add(new PageTextImage("This will heat up", new ResourceLocation(Reference.MODID, "textures/misc/calon.png"), false));
        TemperaturePage.add(new PageTextImage("This will also heat up", new ResourceLocation(Reference.MODID, "textures/misc/cal2on.png"), false));
        TemperaturePage.add(new PageTextImage("This wont heat up", new ResourceLocation(Reference.MODID, "textures/misc/cal2off.png"), false));
        TemperaturePage.add(new PageText("If the water reaches a temperature of " + TextFormatting.DARK_RED + "101"  + TextFormatting.WHITE + " then the brewery will start losing water, and if the water gets to zero then you lose everything and the brewery will cool down to 22 Degrees"));
        TemperaturePage.add(new PageTextImage("Since it has water it will heat up", new ResourceLocation(Reference.MODID, "textures/misc/calt.png"), false));
        TemperaturePage.add(new PageTextImage("Losing water because its over 100 Degrees", new ResourceLocation(Reference.MODID, "textures/misc/calton.png"), false));
        cauldron.put(new ResourceLocation(Reference.MODID, "CEntry2"), new Entry(TemperaturePage, "Temperature"));

        List<IPage> SplashPage = new ArrayList<IPage>();
        SplashPage.add(new PageText("Due to the valuable nature of the splash potions from the cauldron, you don't just instantly chuck the potion out but rather you §4AIM§0 them" +
                "\n\nTo Aim simply hold right click and let go to throw, the longer you hold it in the farther it will go"));
        cauldron.put(new ResourceLocation(Reference.MODID, "CEntry3"), new EntryItemStack(SplashPage, "Splash Potions", new ItemStack(ModItems.potionSplash)));


        /**
         * Adding Ingredient pages
         */
        List<IPage> IngredientFirstPage = new ArrayList<IPage>();
        IngredientFirstPage.add(new PageText("How the ingredient pages are Read:" +
                "\n" +
                "\nPotion Effect:" +
                "\nThe effect the ingredient has on the potion is listed here" +
                "\n" +
                "\nIngredient:" +
                "\nThe Item/Block listed here is the ingredient"));
        IngredientFirstPage.add(new PageText(
                "\nIdeal Temperature: x " +
                "\nWhere x is the ideal Temperature of the ingredient in the brew"));
        ingredients.put(new ResourceLocation(Reference.MODID, "entry0"), new Entry(IngredientFirstPage, "Ingredient Page Layout"));
        for(int i = 1; i < Reference.EFFECT_ITEMS.length; i++){
            List<IPage> page = new ArrayList<IPage>();
            page.add(new PageItemStack("Potion Effect:\n" +  I18n.translateToLocal(Potion.getPotionById(i).getName()) +
                    "\n\nIngredient:\n" +  Reference.EFFECT_ITEMS[i].getItemStackDisplayName(new ItemStack(Reference.EFFECT_ITEMS[i])) +
                    "\n\nIdeal Temperature: " + Reference.EFFECT_TEMPRETURES[i], new ItemStack(Reference.EFFECT_ITEMS[i])));
            ingredients.put(new ResourceLocation(Reference.MODID, "entry"+i), new EntryItemStack(page, I18n.translateToLocal(Potion.getPotionById(i).getName()), new ItemStack(Reference.EFFECT_ITEMS[i])));
        }

        /**
         * Adding Toxicity Pages
         */

        List<IPage> ToxicFirstPage = new ArrayList<IPage>();
        ToxicFirstPage.add(new PageText("Potions brewed in the Cauldron are impure by nature due to contaminants present in the water and ingredients themselves" +
                "\n" +
                "\nWhen Drinking a potion your body will get " + I18n.translateToLocal("brewery.category.toxicity.contamination") + " for a while" +
                "\n\n"));
        ToxicFirstPage.add(new PageText("There are two stages of being " + I18n.translateToLocal("brewery.category.toxicity.contamination") + " and drinking any more potions will contaminate your body so much that you will become " + I18n.translateToLocal("brewery.category.toxicity.toxic") +
                "\n\nWhen you are " + I18n.translateToLocal("brewery.category.toxicity.toxic") + " trying to drink another potion will either not work or end in dire consequences"));
        toxicity.put(new ResourceLocation(Reference.MODID, "TEntry0"), new Entry(ToxicFirstPage, "Toxicity Basics"));

        List<IPage> ToxicPassivePage = new ArrayList<IPage>();
        ToxicPassivePage.add(new PageText("If your contaminated to the point of being " + I18n.translateToLocal("brewery.category.toxicity.toxic") + ", your body will try and cleans itself of the magic by trying manifesting. \n\nBrewer's call this effect the §5Toxic Flu§0\n\n" +
                "Since your body is purifying " + TextFormatting.OBFUSCATED + TextFormatting.DARK_PURPLE +"Wild" + TextFormatting.WHITE + " Magic, it will manifest it self randomly as short potent potion efects"));
        toxicity.put(new ResourceLocation(Reference.MODID, "TEntry1"), new Entry(ToxicPassivePage, "Toxic Flu"));

        List<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(new CategoryItemStack(cauldron, I18n.translateToLocal("book.category.cauldron"), new ItemStack(ModItems.potionLarge)));
        categories.add(new CategoryItemStack(ingredients, I18n.translateToLocal("book.category.ingredients"), new ItemStack(Items.BLAZE_POWDER)));
        categories.add(new CategoryItemStack(toxicity, I18n.translateToLocal("book.category.toxicity"), new ItemStack(Items.SPIDER_EYE)));

        breweryGuide = new Book();
        breweryGuide.setTitle("A Players Guide to Brewing");
        breweryGuide.setDisplayName("A Players Guide to Brewing");
        breweryGuide.setAuthor("Team Avion");
        breweryGuide.setColor(Color.RED);
        breweryGuide.setCategoryList(categories);
        breweryGuide.setRegistryName(new ResourceLocation(Reference.MODID, "guide"));
        return breweryGuide;
    }

    /**
     * Use this to handle setting the model of your book. Only exists on the client.
     *
     * @param bookStack - The ItemStack assigned to your book.
     */
    @Override
    public void handleModel(ItemStack bookStack) {
        GuideAPI.setModel(breweryGuide);
    }

    /**
     * Called during Post Initialization. Use this to register recipes and the like.
     *
     * @param bookStack
     */
    @Override
    public void handlePost(ItemStack bookStack) {
        GameRegistry.addShapelessRecipe(bookStack, Items.BOOK, ModItems.bottleSmall);
        GameRegistry.addShapelessRecipe(bookStack, Items.BOOK, ModItems.bottleMedium);
        GameRegistry.addShapelessRecipe(bookStack, Items.BOOK, ModItems.bottleLarge);
    }
}
