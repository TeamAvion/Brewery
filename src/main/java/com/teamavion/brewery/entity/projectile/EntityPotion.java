package com.teamavion.brewery.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityPotion extends EntityThrowable {

    private ItemStack potion;

    public EntityPotion(World worldIn, EntityLivingBase throwerIn, ItemStack potion) {
        super(worldIn, throwerIn);
        this.potion = potion;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }
}
