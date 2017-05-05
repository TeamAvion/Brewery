package org.teamavion.brewery.proxy;


import org.teamavion.brewery.block.ModBlocks;
import org.teamavion.brewery.item.ModItems;

@SuppressWarnings("unused")
public class ClientProxy implements CommonProxy {

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
    }

    @Override
    public void postInit() {

    }
}
