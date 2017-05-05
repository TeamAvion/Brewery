package org.teamavion.brewery.item;

import org.teamavion.brewery.Reference;
import net.minecraft.item.Item;

public class ItemBasic extends Item {

    ItemBasic(String unlocalizedName, String registryName, int maxStackSize) {
        setCreativeTab(Reference.BREWERY_TAB);
        setMaxStackSize(maxStackSize);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }
}