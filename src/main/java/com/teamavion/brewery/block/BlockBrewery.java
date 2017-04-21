package com.teamavion.brewery.block;

import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.tile.TileBrewery;
import com.teamavion.brewery.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBrewery extends Block implements ITileEntityProvider {

    public BlockBrewery(){
        super(Material.IRON);
        setUnlocalizedName("brewery");
        setRegistryName("blockbrewery");
        setHarvestLevel("pickaxe", 1);
        setResistance(30F);
        setHardness(5F);
        setCreativeTab(Reference.tabBrewery);
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
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(0, false))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(ModItems.bottleMedium.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery)
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(1, false))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(ModItems.bottleLarge.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery)
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(2, false))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(ModItems.bottleSplash.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery)
                    if(((TileBrewery)worldIn.getTileEntity(pos)).createPotion(0, true))
                        playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
            if((playerIn.getHeldItemMainhand().getItem() != null) && (playerIn.getHeldItemMainhand().getItem().getUnlocalizedName().equals(Items.WATER_BUCKET.getUnlocalizedName())))
                if(worldIn.getTileEntity(pos) instanceof TileBrewery){
                     if(((TileBrewery)worldIn.getTileEntity(pos)).addWater())
                         playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
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

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }
    */

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) { return false; }
}