package org.teamavion.brewery.block;

import org.teamavion.brewery.block.tile.TileBrewery;
import org.teamavion.brewery.client.RenderTileBrewery;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

public class ModBlocks {

    public static Block brewery;
    public static Block darkstone;

    public static void init(){
        brewery = new BlockBrewery();
        darkstone = new BlockDarkstone();
    }

    public static void register(){
        //Blocks
        registerBlock(brewery);
        registerBlock(darkstone);

        registerTileEntity(TileBrewery.class, "brewery");
    }

    private static void registerBlock(Block block) {
        GameRegistry.register(block);
        Item item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }
    @SideOnly(Side.CLIENT)
    public static void registerRenders(){
        registerRender(brewery);
        registerRender(darkstone);

        ClientRegistry.bindTileEntitySpecialRenderer(TileBrewery.class, new RenderTileBrewery());
    }

    private static void registerRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}