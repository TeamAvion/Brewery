package com.teamavion.brewery.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBasic extends Item {

    ItemBasic(String unlocalizedName, String registryName, int maxStackSize) {
        setCreativeTab(CreativeTabs.BREWING);
        setMaxStackSize(maxStackSize);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }
}