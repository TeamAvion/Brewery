package com.teamavion.brewery.item;

import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.tile.TileBrewery;
import com.teamavion.brewery.potion.ModPotionEffects;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ItemPotion extends Item {

    private String bottleSize;

    ItemPotion(String unlocalizedName, String registryName, int maxDamage) {
        setCreativeTab(CreativeTabs.BREWING);
        setMaxStackSize(1);
        setMaxDamage(maxDamage);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);

        switch (maxDamage) {
            case 2:
                bottleSize = "small";
                break;
            case 4:
                bottleSize = "medium";
                break;
            case 6:
                bottleSize = "large";
                break;
        }

        //Dynamic texture swapping
        switch (bottleSize) {
            case "small":
                addPropertyOverride(new ResourceLocation("potionsmall-after-use-1"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 1 ? 0.0F : 1.0F);
                break;
            case "medium":
                addPropertyOverride(new ResourceLocation("potionmedium-after-use-1"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 1 ? 0.0F : 1.0F);
                addPropertyOverride(new ResourceLocation("potionmedium-after-use-2"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 2 ? 0.0F : 1.0F);
                addPropertyOverride(new ResourceLocation("potionmedium-after-use-3"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 3 ? 0.0F : 1.0F);
                break;
            case "large":
                addPropertyOverride(new ResourceLocation("potionlarge-after-use-1"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 1 ? 0.0F : 1.0F);
                addPropertyOverride(new ResourceLocation("potionlarge-after-use-2"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 2 ? 0.0F : 1.0F);
                addPropertyOverride(new ResourceLocation("potionlarge-after-use-3"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 3 ? 0.0F : 1.0F);
                addPropertyOverride(new ResourceLocation("potionlarge-after-use-4"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 4 ? 0.0F : 1.0F);
                addPropertyOverride(new ResourceLocation("potionlarge-after-use-5"),
                        (stack, worldIn, entityIn) -> stack.getItemDamage() == 5 ? 0.0F : 1.0F);
                break;
        }
    }

    //After potion has been consumed
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        //If executed from client, return
        if(worldIn.isRemote) {
            return stack;
        }

        //If player has effect high toxic, apply random negative effect or do nothing
        if(entityLiving.getActivePotionEffect(ModPotionEffects.highToxic) != null){
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

        if(entityLiving.getActivePotionEffect(ModPotionEffects.lowToxic) != null) {
            entityLiving.addPotionEffect(new PotionEffect(ModPotionEffects.lowToxic, 600 * 10));
            entityLiving.addPotionEffect(new PotionEffect(ModPotionEffects.highToxic, 600 * 6));
        } else
            entityLiving.addPotionEffect(new PotionEffect(ModPotionEffects.lowToxic, 600*2));

        //Get and apply potion effect
        if(stack.getTagCompound() != null) {
            for(int i = 0; i < TileBrewery.CAPACITY; i++)
                if (stack.getTagCompound().hasKey("potion_ID_"+i, 99))
                    addPotionEffect(entityLiving, stack.getTagCompound().getInteger("potion_ID_"+i), stack.getTagCompound().getShort("potion_grade_"+i), Reference.EFFECTS_SCALABLE[stack.getTagCompound().getInteger("potion_ID_"+i)]);
        }

        //Remove durability
        stack.damageItem(1, entityLiving);

        //If potion is empty, return bottle
        if (stack.getItemDamage() == stack.getMaxDamage()) {
            switch (bottleSize) {
                case "small":
                    return new ItemStack(ModItems.bottleSmall);
                case "medium":
                    return new ItemStack(ModItems.bottleMedium);
                case "large":
                    return new ItemStack(ModItems.bottleLarge);
            }
        }
        return stack;
    }

    private void addPotionEffect(EntityLivingBase entityLiving, int id, short grade, boolean isScalable) {
        if(isScalable)
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeScalable((char)grade), Reference.amplification((char)grade)));
        else
            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(id), Reference.durationFromGradeNotScalable((char)grade)));
    }

    //Set item name
    public String getItemStackDisplayName(ItemStack stack) {
        //If item has no NBT
        if(stack.getTagCompound() == null) {
            switch (bottleSize) {
                case "small":
                    return "Small Inconceivable Potion";
                case "medium":
                    return "Medium Inconceivable Potion";
                case "large":
                    return "Large Inconceivable Potion";
            }
        }
        //Else
        switch (bottleSize) {
            case "small":
                return "Small Custom Potion";
            case "medium":
                return "Medium Custom Potion";
            case "large":
                return "Large Custom Potion";
        }
        return "This should never happen";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
        String a ="";
        if(stack.getTagCompound() != null)
            for(int i = 0; i < TileBrewery.CAPACITY; i++)
                if (stack.getTagCompound().hasKey("potion_ID_"+i, 99)){
                    if(Reference.EFFECTS_SCALABLE[stack.getTagCompound().getInteger("potion_ID_"+i)])
                        a = " " + Reference.amplification((char)stack.getTagCompound().getShort("potion_grade_"+i)) + " ";
                    tooltip.add(I18n.translateToLocal(Potion.getPotionById(stack.getTagCompound().getInteger("potion_ID_"+i)).getName())+ a +": (" + Reference.durationFromGradeNotScalable(((char)stack.getTagCompound().getShort("potion_grade_"+i))) + ")");
                    a="";
    }}

    //Set animation to drink
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    //Set usage time
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
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