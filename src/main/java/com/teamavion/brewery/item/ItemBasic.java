package com.teamavion.brewery.item;

import com.teamavion.brewery.Reference;
import net.minecraft.item.Item;

public class ItemBasic extends Item {

    ItemBasic(String unlocalizedName, String registryName, int maxStackSize) {
        setCreativeTab(Reference.tabBrewery);
        setMaxStackSize(maxStackSize);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }
}