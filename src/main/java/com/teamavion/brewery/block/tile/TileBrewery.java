package com.teamavion.brewery.block.tile;

import com.teamavion.brewery.item.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.teamavion.brewery.recipe.BreweryRecipeHandler.getPotionGrade;
import static com.teamavion.brewery.recipe.BreweryRecipeHandler.getPotionId;
import static com.teamavion.brewery.recipe.BreweryRecipeHandler.isIngredient;

/**
 * Created by TjKenMate on 4/15/2017XD
 */
public class TileBrewery extends TileEntity implements ITickable {
    private int liquidMB, temperature, ingredient, time,
            ingredient1PotionID = -100, ingredient1Ammount, ingredient1Tempreture, ingredient1Time = -100,
            ingredient2PotionID = -100, ingredient2Ammount, ingredient2Tempreture, ingredient2Time = -100,
            ingredient3PotionID = -100, ingredient3Ammount, ingredient3Tempreture, ingredient3Time = -100;
    private boolean temperatureSwitchOn;

    public TileBrewery(){
        ingredient1Ammount = 0;
        ingredient2Ammount = 0;
        ingredient3Ammount = 0;
        temperature = 22;
        liquidMB = 0;
        //For Testing Purposes only
        ingredient1PotionID = 1;
    }

    @Override
    public void update() {
        if(!world.isRemote){
            captureItems();
            //System.out.println("im ruining update");
            if(!temperatureSwitchOn && isLit()){
                time = 0;
                temperatureSwitchOn = true;
            }
            if(temperatureSwitchOn && !isLit()){
                time = 0;
                temperatureSwitchOn = false;
            }
            if(isLit()) {
                if(time < 1000)
                    time++;
                if((time >= timeToIncrease()) && temperature < 101) {
                    temperature++;
                    time = 0;
                }
            }
            if(!isLit()){
                if(time < 1000)
                    time++;
                if((time >= timeToDecrease()) && temperature > 22){
                    temperature--;
                    time = 0;
                }
            }
            if((temperature >= 100) && liquidMB > 0)
                liquidMB--;
            if(ingredient1Time != -100)
                ingredient1Time++;
            if(ingredient2Time != -100)
                ingredient2Time++;
            if(ingredient3Time != -100)
                ingredient3Time++;
            //System.out.println("time: " + time + " temp: " + temperature);
        }
    }
   public boolean createPotion() {
        EntityItem potionEntity = new EntityItem(world, this.getPos().getX(), this.getPos().getY() + 0.25, this.getPos().getZ(),
                new ItemStack(ModItems.potion, 1,0, formPotionNBT()));
        potionEntity.motionY = ThreadLocalRandom.current().nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
        world.spawnEntity(potionEntity);
        return true;
    }

  private NBTTagCompound formPotionNBT(){
        NBTTagCompound compound = new NBTTagCompound();
        if(ingredient1PotionID != -100){
            compound.setInteger("potion_ID_1", ingredient1PotionID);
            compound.setShort("potion_grade_1", getPotionGrade(ingredient1PotionID, ingredient1Ammount, ingredient1Time, ingredient1Tempreture, 5, false, false));
        }
        if(ingredient2PotionID != -100){
            compound.setInteger("potion_ID_2", ingredient2PotionID);
            compound.setShort("potion_grade_2", getPotionGrade(ingredient2PotionID, ingredient2Ammount, ingredient2Time, ingredient2Tempreture, 5, false, false));
        }
        if(ingredient3PotionID != -100){
            compound.setInteger("potion_ID_3", ingredient3PotionID);
            compound.setShort("potion_grade_3", getPotionGrade(ingredient3PotionID, ingredient3Ammount, ingredient3Time, ingredient3Tempreture, 5, false, false));
        }
        return compound;
    }

    public void captureItems() {
        List<EntityItem> entityItems = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(new BlockPos(this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ()), this.getPos()));
        for (int i = 0; i < entityItems.size(); i++) {
            System.out.println("There is an Item on top of me");
            if (addIngredient(entityItems.get(i).getEntityItem().getItem())) {
                ItemStack hold = entityItems.get(i).getEntityItem().copy();
                entityItems.get(i).setDead();
                if (hold.getCount() > 1) {
                    hold.setCount(hold.getCount() - 1);
                    world.spawnEntity(new EntityItem(world, this.getPos().getX(), this.getPos().getY() + 0.5, this.getPos().getZ(), hold));
                }
            }
        }
    }

    private boolean isLit(){
        return !world.isRemote && world.getBlockState(this.getPos().down()).getBlock() == Blocks.FIRE || !world.isRemote && world.getBlockState(this.getPos().down(2)).getBlock() == Blocks.FIRE && !world.getBlockState(this.getPos().down()).isFullBlock();
    }

    private int timeToIncrease(){
        return 180;
    }

    private int timeToDecrease(){
        return 180;
    }

    public boolean addIngredient(Item input) {
        if (isIngredient(input)) {
            if (ingredient < 9) {
                if (ingredient1PotionID == -100) {
                    ingredient1PotionID = getPotionId(input);
                    ingredient1Ammount = 1;
                    ingredient++;
                    ingredient1Time = 0;
                    return   true;
                } else if (getPotionId(input) == ingredient1PotionID) {
                    ingredient1Ammount++;
                    ingredient++;
                    ingredient1Time = getNewIngredientTime(1);
                    return true;
                } else if (ingredient2PotionID == -100) {
                    ingredient2PotionID = getPotionId(input);
                    ingredient2Ammount = 1;
                    ingredient++;
                    ingredient2Time = 0;
                    return true;
                } else if (getPotionId(input) == ingredient2PotionID) {
                    ingredient2Ammount++;
                    ingredient++;
                    ingredient2Time = getNewIngredientTime(2);
                    return true;
                } else if (ingredient3PotionID == -100) {
                    ingredient3PotionID = getPotionId(input);
                    ingredient3Ammount = 1;
                    ingredient++;
                    ingredient3Time = 0;
                    return true;
                } else if (getPotionId(input) == ingredient3PotionID) {
                    ingredient3Ammount++;
                    ingredient++;
                    ingredient3Time = getNewIngredientTime(3);
                    return true;
                }
            }
        }
        return false;
    }

    private int getNewIngredientTime(int ingredientNumber){
        if (ingredientNumber == 1)
            return 0;
        if (ingredientNumber == 2)
            return 0;
        if (ingredientNumber == 3)
            return 0;
        return -256856852;
    }
}
