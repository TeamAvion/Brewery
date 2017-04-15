package com.teamavion.brewery.proxy;


import com.teamavion.brewery.block.ModBlocks;

@SuppressWarnings("unused")
public class ClientProxy implements CommonProxy {

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
        ModBlocks.registerRenders();
        //ModItems.registerRenders();
    }

    @Override
    public void postInit() {

    }
}
