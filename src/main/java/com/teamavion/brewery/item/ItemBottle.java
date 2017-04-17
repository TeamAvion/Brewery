package com.teamavion.brewery.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by White Autumn on 2017-04-17.
 */
public class ItemBottle extends Item {

    ItemBottle(String unlocalizedName, String registryName) {
        setCreativeTab(CreativeTabs.BREWING);
        setMaxStackSize(16);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }
}
