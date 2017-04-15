package com.teamavion.brewery.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Created by TjKenMate on 4/15/2017XD
 */
public class TileBrewery extends TileEntity implements ITickable {
    private int liquidMB, tempreture, ingredient, time,
            ingredient1PotionID, ingredient1Ammount, ingredient1Tempreture, ingredient1Time,
            ingredient2PotionID, ingredient2Ammount, ingredient2Tempreture, ingredient2Time,
            ingredient3PotionID, ingredient3Ammount, ingredient3Tempreture, ingredient3Time;
    private boolean tempretureSwitchOn;
    public TileBrewery(){

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
                if(time >= timeToIncrease())
                    tempreture++;
            }
            if(!isLit()){
                time++;
                if((time >= timeToDecrease()) && tempreture > 25)
                    tempreture--;
            }
        }
    }

    public static boolean isLit(){
        return true;
    }

    private int timeToIncrease(){
        return 25;
    }

    private int timeToDecrease(){
        return 25;
    }
}
