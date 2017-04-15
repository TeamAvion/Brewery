package com.teamavion.brewery.block;

import com.teamavion.brewery.block.tile.TileBrewery;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by TjKenMate on 4/15/2017.
 */
public class BlockBrewery extends Block implements ITileEntityProvider {

    public BlockBrewery(){
        super(Material.IRON);
        setUnlocalizedName("brewery");
        setRegistryName("BlockBrewery");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBrewery();
    }

}
