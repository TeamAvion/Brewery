package com.teamavion.brewery.item;

import com.teamavion.brewery.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by WhiteAutumn on 2017-04-15.
 */
public class ItemPotion extends Item {

    private int iPotion1, iPotion2, iPotion3, time;
    private char cPotion1, cPotion2, cPotion3;

    //TODO: Add durability like flint and steal
    public ItemPotion() {
        setMaxDamage(4);
        setMaxStackSize(1);
        setUnlocalizedName("potion");
        setRegistryName("ItemPotion");
        iPotion1 = 1;
        iPotion2 = -100;
        iPotion3 = -100;
        cPotion1 = 'A';
        cPotion2 = '@';
        cPotion3 = '@';
        time = 10;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (!worldIn.isRemote) {
            if (iPotion1 != -100)
                addPotion(entityLiving,iPotion1, cPotion1, false);
            if (iPotion2 != -100)
                addPotion(entityLiving,iPotion2, cPotion2, false);
            if (iPotion3 != -100)
                addPotion(entityLiving,iPotion3, cPotion3, false);
            stack.damageItem(1, entityLiving);
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            return stack;
        }
        return stack;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return "Unknown Potion";
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    private void addPotion(EntityLivingBase entityLiving, int id, char grade, boolean isScalable){
       if(isScalable)
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeScalable(grade), Reference.amplification(grade)));
       else
           entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeNotScalable(grade)));
    }
}