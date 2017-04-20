package com.teamavion.brewery.item;

import com.teamavion.brewery.entity.projectile.EntityPotion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemPotionSplash extends Item {

    public ItemPotionSplash(String unlocalizedName, String registryName) {
        setCreativeTab(CreativeTabs.BREWING);
        setMaxStackSize(1);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (worldIn.isRemote) {
            return;
        }

        EntityPlayer entityPlayer = (EntityPlayer) entityLiving;

        EntityPotion entityPotion = new EntityPotion(worldIn, entityPlayer, stack);
        entityPotion.setHeadingFromThrower(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, -20.0F, 0.5F, 1.0F); //TODO: MAKE VELOCITY BASED ON timeLeft
        worldIn.spawnEntity(entityPotion);
        System.out.println("!AFTER ENTITY POTION SPAWN SHOULD HAVE HAPPENED");

        stack.setCount(0);
    }

    //Set animation to bow
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    //Set usage time
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}