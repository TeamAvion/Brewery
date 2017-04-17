package com.teamavion.brewery.block;

import com.teamavion.brewery.block.tile.TileBrewery;
import com.teamavion.brewery.item.ModItems;
import com.teamavion.brewery.recipe.BreweryRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by TjKenMate on 4/15/2017.
 */
public class BlockBrewery extends Block implements ITileEntityProvider {

    public BlockBrewery(){
        super(Material.IRON);
        setUnlocalizedName("brewery");
        setRegistryName("blockbrewery");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(worldIn.isRemote){ return true; }
        if(worldIn.getTileEntity(pos) == null){ return false;}
        if(worldIn.getTileEntity(pos) instanceof TileBrewery){
            if(((TileBrewery) worldIn.getTileEntity(pos)).addIngredient(playerIn.getHeldItemMainhand().getItem())){
                playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            }
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(ModItems.bottleSmall.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery)
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(0))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(ModItems.bottleMedium.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery)
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(1))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(ModItems.bottleLarge.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery)
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(2))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(Items.WATER_BUCKET.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery){
                    // if(((TileBrewery)worldIn.getTileEntity(pos)).addWater(1000))
                    //playerIn.getHeldItemMainhand().setCount(0);
                }
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBrewery();
    }
    /**
     @Override
     public boolean isFullyOpaque(IBlockState state)
     {
     return true;
     }

     @Override
     @Deprecated
     @SideOnly(Side.CLIENT)
     public boolean isTranslucent(IBlockState state) { return true; }
     */

}