package org.teamavion.brewery.client;

import org.teamavion.brewery.block.tile.TileBrewery;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTileBrewery extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {

        if (!(tileEntity instanceof TileBrewery)) {
            return;
        }

        String iTextComponent = "Temperature: " + ((TileBrewery) tileEntity).getTemperature() + " WaterLvl: " +((TileBrewery) tileEntity).getLiquidMB();
        if (this.rendererDispatcher.cameraHitResult != null && tileEntity.getPos().equals(this.rendererDispatcher.cameraHitResult.getBlockPos()))
        {
            this.setLightmapDisabled(true);
            this.drawNameplate(tileEntity, iTextComponent, x, y, z, 12);
            this.setLightmapDisabled(false);
        }

        /*
        if(watt_her > 0){
         ResourceLocation image = new ResourceLocation(Reference.MODID + ":waterStill");
         this.bindTexture(image);
         Tessellator t = Tessellator.getInstance();
         GL11.glPushMatrix();
         GL11.glTranslated(x, y + 1, z);
        }
         */
    }

    public void drawNameplate(TileEntity te, String str, double x, double y, double z, int maxDistance) {
        Entity entity = this.rendererDispatcher.entity;
        double d0 = te.getDistanceSq(entity.posX, entity.posY, entity.posZ);

        if (d0 <= (double)(maxDistance * maxDistance))
        {
            float f = this.rendererDispatcher.entityYaw;
            float f1 = this.rendererDispatcher.entityPitch;
            EntityRenderer.drawNameplate(this.getFontRenderer(), str, (float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F, 0, f, f1, false, false);
        }
    }
}