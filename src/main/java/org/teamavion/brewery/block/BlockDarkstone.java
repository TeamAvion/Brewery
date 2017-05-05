package org.teamavion.brewery.block;

import org.teamavion.brewery.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by TjKenMate on 4/20/2017.
 */
public class BlockDarkstone extends Block {

    public BlockDarkstone() {
        super(Material.IRON);
        setUnlocalizedName("darkstone");
        setRegistryName("blockdarkstone");
        setHarvestLevel("pickaxe", 1);
        setResistance(30F);
        setHardness(5F);
        setCreativeTab(Reference.BREWERY_TAB);
    }
}
