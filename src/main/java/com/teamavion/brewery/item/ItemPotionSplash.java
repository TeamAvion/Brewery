package com.teamavion.brewery.item;

import com.teamavion.brewery.entity.projectile.EntityPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPotionSplash extends Item {

    public ItemPotionSplash(String unlocalizedName, String registryName) {
        setMaxStackSize(3);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (worldIn.isRemote) {
            return;
        }

        EntityPlayer entityPlayer = (EntityPlayer) entityLiving;

        EntityPotion entityPotion = new EntityPotion(worldIn, entityPlayer, stack);
        entityPotion.setHeadingFromThrower(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, -20.0F, 0.03F * ((getMaxItemUseDuration(stack)-timeLeft) < 32 ? (getMaxItemUseDuration(stack)-timeLeft) : 32), 0.0F);
        worldIn.spawnEntity(entityPotion);
        worldIn.playSound(null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        stack.setCount(stack.getCount()-1);
    }

    //Set animation to bow
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    //Set usage time
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    //Set item glow
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}