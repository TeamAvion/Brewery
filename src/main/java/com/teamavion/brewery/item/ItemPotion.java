package com.teamavion.brewery.item;

import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.tile.TileBrewery;
import com.teamavion.brewery.potion.ModPotions;
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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by WhiteAutumn on 2017-04-16.
 */

public class ItemPotion extends Item {

    ItemPotion(String unlocalizedName, String registryName, int maxDamage) {
        setCreativeTab(CreativeTabs.BREWING);
        setMaxStackSize(1);
        setMaxDamage(maxDamage);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(worldIn.isRemote) {
            return stack;
        }

        if(entityLiving.getActivePotionEffect(ModPotions.highToxic) != null){
            int result = ThreadLocalRandom.current().nextInt(6);
            switch (result) {
                case 0:
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(20),600*5, 100));
                    break;
                case 1:
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(9),600*10));
                    break;
                case 3:
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(9),600*5));
                    break;
                case 2:
                    return stack;
                case 4:
                    return stack;
                case 5:
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(9),600*10));
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(20),600, 10));
                    break;
            }
        }

        if(entityLiving.getActivePotionEffect(ModPotions.lowToxic) != null) {
            entityLiving.addPotionEffect(new PotionEffect(ModPotions.lowToxic, 600 * 10));
            entityLiving.addPotionEffect(new PotionEffect(ModPotions.highToxic, 600 * 6));
        } else
            entityLiving.addPotionEffect(new PotionEffect(ModPotions.lowToxic, 600*2));

        ItemStack death = null;
        if(stack.getMaxDamage() == 2)
            death = new ItemStack(ModItems.bottleSmall);
        if(stack.getMaxDamage() == 4)
            death = new ItemStack(ModItems.bottleMedium);
        if(stack.getMaxDamage() == 6)
            death = new ItemStack(ModItems.bottleLarge);

        if(stack.getTagCompound() != null) {
            for(int i = 0; i < TileBrewery.INGREDIENTLIMIT; i++)
                if (stack.getTagCompound().hasKey("potion_ID_"+i, 99))
                    addPotion(entityLiving, stack.getTagCompound().getInteger("potion_ID_"+i), stack.getTagCompound().getShort("potion_grade_"+i), Reference.EFFECTS_SCALABLE[stack.getTagCompound().getInteger("potion_ID_"+i)]);
        }

        stack.damageItem(1, entityLiving);
        if (stack.getItemDamage() == stack.getMaxDamage()) {
            return death;
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
        if(stack.getTagCompound() == null)
                return "Is Inconceivable";
        return "Custom Potion";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
        String a ="";
        if(stack.getTagCompound() != null)
            for(int i = 0; i < TileBrewery.INGREDIENTLIMIT; i++)
                if (stack.getTagCompound().hasKey("potion_ID_"+i, 99)){
                    if(Reference.EFFECTS_SCALABLE[stack.getTagCompound().getInteger("potion_ID_"+i)])
                        a = " " + Reference.amplification((char)stack.getTagCompound().getShort("potion_grade_"+i)) + " ";
                    tooltip.add(I18n.translateToLocal(Potion.getPotionById(stack.getTagCompound().getInteger("potion_ID_"+i)).getName())+ a +": (" + Reference.durationFromGradeNotScalable(((char)stack.getTagCompound().getShort("potion_grade_"+i))) + ")");
                    a="";
    }}

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