package com.teamavion.brewery.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.concurrent.ThreadLocalRandom;

import static com.teamavion.brewery.recipe.BreweryRecipeHandler.getPotionId;
import static com.teamavion.brewery.recipe.BreweryRecipeHandler.isIngredient;

/**
 * Created by TjKenMate on 4/15/2017XD
 */
public class TileBrewery extends TileEntity implements ITickable {
    private int liquidMB, tempreture, ingredient, time,
            ingredient1PotionID = -100, ingredient1Ammount, ingredient1Tempreture, ingredient1Time = -100,
            ingredient2PotionID = -100, ingredient2Ammount, ingredient2Tempreture, ingredient2Time = -100,
            ingredient3PotionID = -100, ingredient3Ammount, ingredient3Tempreture, ingredient3Time = -100;
    private boolean tempretureSwitchOn;

    public TileBrewery(){
        ingredient1Ammount = 0;
        ingredient2Ammount = 0;
        ingredient3Ammount = 0;
        tempreture = 22;
        liquidMB = 0;
    }

    @Override
    public void update() {
        if(!world.isRemote){
            if(!tempretureSwitchOn && isLit()){
                time = 0;
                tempretureSwitchOn = true;
            }
            if(tempretureSwitchOn && !isLit()){
                time = 0;
                tempretureSwitchOn = false;
            }
            if(isLit()) {
                time++;
                if((time >= timeToIncrease()) && tempreture < 101)
                    tempreture++;
            }
            if(!isLit()){
                time++;
                if((time >= timeToDecrease()) && tempreture > 22)
                    tempreture--;
            }
            if((tempreture >= 100) && liquidMB > 0)
                liquidMB--;
            if(ingredient1Time != -100)
                ingredient1Time++;
            if(ingredient2Time != -100)
                ingredient2Time++;
            if(ingredient3Time != -100)
                ingredient3Time++;
        }
    }

    private boolean isLit(){
        return true;
    }

    private int timeToIncrease(){
        return 25;
    }

    private int timeToDecrease(){
        return 25;
    }

    private boolean createPotion(){
        EntityItem potionEntity = new EntityItem(world, this.getPos().getX(), this.getPos().getY() + 0.5, this.getPos().getZ(),
                new ItemStack(Items.ARROW));
        potionEntity.motionY = ThreadLocalRandom.current().nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
        world.spawnEntity(potionEntity);
    }

    private boolean addIngredient(Item input) {
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
