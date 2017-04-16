package com.teamavion.brewery.block.tile;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by TjKenMate on 4/16/2017.
 */
public class TileBreweryRenderer extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage){

        String itextcomponent = null;

        if(te instanceof TileBrewery){
            itextcomponent = "Temperature: " + ((TileBrewery) te).getTemperature();
        }

        if (itextcomponent != null && this.rendererDispatcher.cameraHitResult != null && te.getPos().equals(this.rendererDispatcher.cameraHitResult.getBlockPos()))
        {
            this.setLightmapDisabled(true);
            this.drawNameplate(te, itextcomponent, x, y, z, 12);
            this.setLightmapDisabled(false);
        }
    }

    protected void drawNameplate(TileEntity te, String str, double x, double y, double z, int maxDistance)
    {
        Entity entity = this.rendererDispatcher.entity;
        double d0 = te.getDistanceSq(entity.posX, entity.posY, entity.posZ);

        if (d0 <= (double)(maxDistance * maxDistance))
        {
            float f = this.rendererDispatcher.entityYaw;
            float f1 = this.rendererDispatcher.entityPitch;
            boolean flag = false;
            EntityRenderer.drawNameplate(this.getFontRenderer(), str, (float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F, 0, f, f1, false, false);
        }
    }
}
