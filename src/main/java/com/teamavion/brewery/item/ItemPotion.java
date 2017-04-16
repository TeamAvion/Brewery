package com.teamavion.brewery.item;

import com.teamavion.brewery.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by WhiteAutumn on 2017-04-16.
 */

public class ItemPotion extends Item {

    ItemPotion() {
        setCreativeTab(CreativeTabs.BREWING);
        setMaxDamage(4);
        setMaxStackSize(1);
        setUnlocalizedName("potion");
        setRegistryName("ItemPotion");
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(worldIn.isRemote){return stack;}
        if(stack.getTagCompound() != null) {
            if (stack.getTagCompound().hasKey("potion_ID_1", 99))
                addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_1"), stack.getTagCompound().getShort("potion_grade_1"), false);
            if (stack.getTagCompound().hasKey("potion_ID_2", 99))
                addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_2"), stack.getTagCompound().getShort("potion_grade_2"), false);
            if (stack.getTagCompound().hasKey("potion_ID_3", 99))
                addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_3"), stack.getTagCompound().getShort("potion_grade_3"), false);
        }
        stack.damageItem(1, entityLiving);
        if (stack.getItemDamage() == stack.getMaxDamage()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        return stack;
    }

    private void addPotion(EntityLivingBase entityLiving, int id, short grade, boolean isScalable) {
        if(isScalable)
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeScalable((char)grade), Reference.amplification((char)grade)));
        else
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeNotScalable((char)grade)));
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return "Unknown Potion";
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}