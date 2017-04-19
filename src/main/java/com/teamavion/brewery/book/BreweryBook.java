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
import amerifrance.guideapi.page.PageItemStack;
import amerifrance.guideapi.page.PageText;
import amerifrance.guideapi.page.PageTextImage;
import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        List<IPage> TemperaturePage = new ArrayList<IPage>();
        TemperaturePage.add(new PageText("To heat up the " + I18n.translateToLocal("brewery.category.cauldron.cauldron.name") + " you need to place a fire either one or two blocks below it with water in it" +
                "\n\nNote: " + I18n.translateToLocal("brewery.category.cauldron.blockinway")));
        TemperaturePage.add(new PageTextImage("This will heat up", new ResourceLocation(Reference.MODID, "textures/misc/calon.png"), false));
        TemperaturePage.add(new PageTextImage("This will also heat up", new ResourceLocation(Reference.MODID, "textures/misc/cal2on.png"), false));
        TemperaturePage.add(new PageTextImage("This §Lwont §0heat up", new ResourceLocation(Reference.MODID, "textures/misc/cal2off.png"), false));
        TemperaturePage.add(new PageText("If the water reaches a temperature of§4 101 §0then the brewery will start losing water, and if the water gets to zero then you lose everything and the brewery will cool down to 22 Degrees"));
        cauldron.put(new ResourceLocation(Reference.MODID, "CEntry1"), new Entry(TemperaturePage, "Temperature"));
        TemperaturePage.add(new PageTextImage("Since it has no water it §Lwont §0heat up", new ResourceLocation(Reference.MODID, "textures/misc/calt.png"), false));
        TemperaturePage.add(new PageTextImage("Losing water because its over 100 Degrees", new ResourceLocation(Reference.MODID, "textures/misc/calton.png"), false));


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
