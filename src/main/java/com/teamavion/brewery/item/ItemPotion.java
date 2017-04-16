package com.teamavion.brewery.item;

import com.teamavion.brewery.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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
public class ItemPotion extends ItemFood {

    //TODO: Add durability like flint and steal
    public ItemPotion() {
        super(0,0,false);
        setAlwaysEdible();
        setMaxDamage(4);
        setMaxStackSize(1);
        setUnlocalizedName("potion");
        setRegistryName("ItemPotion");
    }

    /**
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        System.out.println("im being called2");
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }
     */

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        /**
         * Old Method
        System.out.println("im being called");
        // (!worldIn.isRemote) {
            if (iPotion1 != -100)
                addPotion(entityLiving,iPotion1, cPotion1, false);
            if (iPotion2 != -100)
                addPotion(entityLiving,iPotion2, cPotion2, false);
            if (iPotion3 != -100)
                addPotion(entityLiving,iPotion3, cPotion3, false);
         */
        if(worldIn.isRemote){return stack;}
        if(stack.getTagCompound().hasKey("potion_ID_1", 99))
            addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_1"), stack.getTagCompound().getShort("potion_grade_1"), false);
        if(stack.getTagCompound().hasKey("potion_ID_2", 99))
            addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_2"), stack.getTagCompound().getShort("potion_grade_2"), false);
        if(stack.getTagCompound().hasKey("potion_ID_3", 99))
            addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_3"), stack.getTagCompound().getShort("potion_grade_3"), false);
        stack.damageItem(1, entityLiving);
        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
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

    private void addPotion(EntityLivingBase entityLiving, int id, short grade, boolean isScalable){
       if(isScalable)
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeScalable((char)grade), Reference.amplification((char)grade)));
       else
           entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeNotScalable((char)grade)));
    }
}