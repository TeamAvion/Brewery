package com.teamavion.brewery.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.teamavion.brewery.Reference;
import com.teamavion.brewery.block.tile.TileBrewery;
import com.teamavion.brewery.potion.CustomPotionHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

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
        addPropertyOverride(new ResourceLocation("afterUse1"),
                (stack, worldIn, entityIn) -> stack.getItemDamage() != 1 ? 0.0F : 1.0F);
        addPropertyOverride(new ResourceLocation("afterUse2"),
                (stack, worldIn, entityIn) -> stack.getItemDamage() != 2 ? 0.0F : 1.0F);
        addPropertyOverride(new ResourceLocation("afterUse3"),
                (stack, worldIn, entityIn) -> stack.getItemDamage() != 3 ? 0.0F : 1.0F);
        addPropertyOverride(new ResourceLocation("afterUse4"),
                (stack, worldIn, entityIn) -> stack.getItemDamage() != 4 ? 0.0F : 1.0F);
        addPropertyOverride(new ResourceLocation("afterUse5"),
                (stack, worldIn, entityIn) -> stack.getItemDamage() != 5 ? 0.0F : 1.0F);
    }

    //After potion has been consumed
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(worldIn.isRemote) {
            return stack;
        }

        //Add toxicity and toxicity side effects
        CustomPotionHandler.addToxicity(entityLiving);

        //Get and apply potion effect
        CustomPotionHandler.addPotionEffectFromNBT(stack.getTagCompound(), entityLiving);

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
                        a = " " + (Reference.amplification((char)stack.getTagCompound().getShort("potion_grade_"+i)) + 1) + " ";
                    tooltip.add(ChatFormatting.GOLD + I18n.translateToLocal(Potion.getPotionById(stack.getTagCompound().getInteger("potion_ID_"+i)).getName())+ a + ChatFormatting.WHITE +":" + " (" + ChatFormatting.AQUA + Reference.durationFromGradeNotScalable(((char)stack.getTagCompound().getShort("potion_grade_"+i)))/600 + ChatFormatting.WHITE + ":" + ChatFormatting.AQUA + String.format("%02d",(Reference.durationFromGradeNotScalable(((char)stack.getTagCompound().getShort("potion_grade_"+i)))%600)/10) + ChatFormatting.WHITE + ")");
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