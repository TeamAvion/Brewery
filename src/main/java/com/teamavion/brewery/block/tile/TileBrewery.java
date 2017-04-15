package com.teamavion.brewery.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Created by TjKenMate on 4/15/2017.
 */
public class TileBrewery extends TileEntity implements ITickable {


    public TileBrewery(){

    }

    @Override
    public void update() {
        if(!world.isRemote){
            if(isLit()) {

            }
        }
    }

    public static boolean isLit(){
        return true;
    }
}
