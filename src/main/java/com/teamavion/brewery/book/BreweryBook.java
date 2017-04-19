package com.teamavion.brewery.book;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.ModBlocks;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
        Map<ResourceLocation, EntryAbstract> toxicity = new LinkedHashMap<ResourceLocation, EntryAbstract>();
        Map<ResourceLocation, EntryAbstract> ingredients = new LinkedHashMap<ResourceLocation, EntryAbstract>();

        List<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(new CategoryItemStack(cauldron, "Brewing", new ItemStack(ModBlocks.brewery)));
        categories.add(new CategoryItemStack(toxicity, "Ingredients", new ItemStack(Items.BLAZE_POWDER)));
        categories.add(new CategoryItemStack(ingredients, "Toxicity", new ItemStack(Items.SPIDER_EYE)));

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
