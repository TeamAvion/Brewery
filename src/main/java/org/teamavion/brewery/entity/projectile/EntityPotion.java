package org.teamavion.brewery.entity.projectile;

import org.teamavion.brewery.item.ModItems;
import org.teamavion.brewery.potion.CustomPotionHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityPotion extends EntityThrowable {

    private ItemStack potion;

    public EntityPotion(World worldIn) {
        super(worldIn);
        potion = new ItemStack(ModItems.potionSplash);
    }

    public EntityPotion(World worldIn, EntityLivingBase throwerIn, ItemStack stack) {
        super(worldIn, throwerIn);
        potion = stack;
    }

    @Override
    public void onImpact(RayTraceResult result) {
        if (world.isRemote) {
            //this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ, new int[0]);
            return;
        }
        applySplash(result);
        world.playEvent(2002, new BlockPos(this), 16253176);
        setDead();
    }

    private void applySplash(RayTraceResult result) {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
        List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

        if (!list.isEmpty()) {
            for (EntityLivingBase entityLiving : list) {
                if (entityLiving.canBeHitWithPotion()) {
                    double distance = this.getDistanceSqToEntity(entityLiving);

                    if (distance < 16.0D) {
                        CustomPotionHandler.addToxicity(entityLiving);
                        CustomPotionHandler.addPotionEffectFromNBT(potion.getTagCompound(), entityLiving);
                    }
                }
            }
        }
    }

    //Falling speed
    public float getGravityVelocity() {
        return 0.05F;
    }
}