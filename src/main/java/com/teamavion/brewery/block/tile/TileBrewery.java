package com.teamavion.brewery.block.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

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
    }

    @Override
    public void update() {
        if(!world.isRemote){
            if(!temperatureSwitchOn && isLit()){
                time = 0;
                temperatureSwitchOn = true;
            }
            if(temperatureSwitchOn && !isLit()){
                time = 0;
                temperatureSwitchOn = false;
            }
            if(isLit()) {
                time++;
                if((time >= timeToIncrease()) && temperature < 101)
                    temperature++;
            }
            if(!isLit()){
                time++;
                if((time >= timeToDecrease()) && temperature > 22)
                    temperature--;
            }
            if((temperature >= 100) && liquidMB > 0)
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
        //TODO: Not tested code, should work
        return world.getBlockState(this.getPos().down()).getBlock() == Blocks.FIRE || world.getBlockState(this.getPos().down(2)).getBlock() == Blocks.FIRE && !world.getBlockState(this.getPos().down()).isFullBlock();
    }

    private int timeToIncrease(){
        return 25;
    }

    private int timeToDecrease(){
        return 25;
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
