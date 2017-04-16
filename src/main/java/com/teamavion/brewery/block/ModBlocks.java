package com.teamavion.brewery.block;

import com.teamavion.brewery.block.tile.TileBrewery;
import com.teamavion.brewery.block.tile.TileBreweryRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

/**
 * Created by TjKenMate on 4/15/2017.
 */
public class ModBlocks {

    public static Block brewery;

    public static void init(){
        brewery = new BlockBrewery();
    }

    public static void register(){
        //blocks
        registerBlock(brewery);

        registerTileEntity(TileBrewery.class, "brewery");
    }

    private static void registerBlock(Block block) {
        GameRegistry.register(block);
        Item item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }

    public static void registerRenders(){
        registerRender(brewery);

        ClientRegistry.bindTileEntitySpecialRenderer(TileBrewery.class, new TileBreweryRenderer());
    }

    private static void registerRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
