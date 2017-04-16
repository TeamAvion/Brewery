package com.teamavion.brewery.block.tile;

import com.teamavion.brewery.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.teamavion.brewery.recipe.BreweryRecipeHandler.getPotionGrade;
import static com.teamavion.brewery.recipe.BreweryRecipeHandler.getPotionId;
import static com.teamavion.brewery.recipe.BreweryRecipeHandler.isIngredient;

/**
 * Created by TjKenMate on 4/15/2017XD
 */
public class TileBrewery extends TileEntity implements ITickable {
    private List<Ingredient> ingredientList = new ArrayList<>(0);
    private int liquidMB, temperature, ingredientCount, time;
    private boolean temperatureSwitchOn;

    public TileBrewery() {
        temperature = 22;
        liquidMB = 0;
        //For Testing Purposes only
        //ingredient1PotionID = 1;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            captureItems();
            //System.out.println("im ruining update");
            if (!temperatureSwitchOn && isLit()) {
                time = 0;
                temperatureSwitchOn = true;
            }
            if (temperatureSwitchOn && !isLit()) {
                time = 0;
                temperatureSwitchOn = false;
            }
            if (isLit()) {
                if (time < 1000)
                    time++;
                if ((time >= timeToIncrease()) && temperature < 101) {
                    temperature++;
                    System.out.println(temperature);
                    time = 0;
                    sync(this);
                }
            }
            if (!isLit()) {
                if (time < 1000)
                    time++;
                if ((time >= timeToDecrease()) && temperature > 22) {
                    temperature--;
                    time = 0;
                    sync(this);
                }
            }
            if ((temperature >= 100) && liquidMB > 0) {
                sync(this);
                liquidMB--;
            }

            for (Ingredient ingredient : ingredientList) {
                ingredient.time++;
                sync(this);
            }
            //System.out.println("time: " + time + " temp: " + temperature);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        temperature = compound.getInteger("temperature");
        liquidMB = compound.getInteger("liquidMB");
        ingredientCount = compound.getInteger("ingredientCount");
        for(int i = 0; i < compound.getInteger("ingredientTypes"); i++){
            compound.getInteger("ingredient_"+i+"_id");
            compound.getInteger("ingredient_"+i+"_amount");
            compound.getInteger("ingredient_"+i+"_temperature");
            compound.getInteger("ingredient_"+i+"_time");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(compound == null)
            return compound;
        compound.setInteger("temperature", temperature);
        compound.setInteger("liquidMB", liquidMB);
        compound.setInteger("ingredientCount", ingredientCount);
        compound.setInteger("ingredientTypes", ingredientList.size());
        for(int i = 0; i < ingredientList.size(); i++){
            ingredientList.add(new Ingredient(compound.getInteger("ingredient_"+i+"_id"),
            compound.getInteger("ingredient_"+i+"_amount"),
            compound.getInteger("ingredient_"+i+"_temperature"),
            compound.getInteger("ingredient_"+i+"_time")));
        }
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    public static void sync(TileEntity tileEntity) {
        if (tileEntity.getWorld() != null) {
            IBlockState state = tileEntity.getWorld().getBlockState(tileEntity.getPos());
            tileEntity.getWorld().notifyBlockUpdate(tileEntity.getPos(), state, state, 3);
        }
    }

   public boolean createPotion() {
        ItemStack potion = new ItemStack(ModItems.potion);
        potion.setTagCompound(formPotionNBT());
        EntityItem potionEntity = new EntityItem(world, this.getPos().getX(), this.getPos().getY() + 0.25, this.getPos().getZ(), potion);
        potionEntity.motionY = ThreadLocalRandom.current().nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
        world.spawnEntity(potionEntity);

       liquidMB = 0;
       temperature = 22;
       ingredientCount = 0;
       ingredientList = new ArrayList<>(0);
       sync(this);

       return true;
    }

  private NBTTagCompound formPotionNBT(){
        NBTTagCompound compound = new NBTTagCompound();
        for (int i = 0; i < ingredientList.size(); i++) {
            compound.setInteger("potion_ID_" + i, ingredientList.get(i).id);
            compound.setShort("potion_grade_" + i, getPotionGrade(ingredientList.get(i).id, ingredientList.get(i).amount, ingredientList.get(i).time, ingredientList.get(i).time,5, false, false));
        }
        return compound;
    }

    public void captureItems() {
        List<EntityItem> entityItems = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(new BlockPos(this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ()), this.getPos()));
        for (int i = 0; i < entityItems.size(); i++) {
            System.out.println("There is an Item on top of me");
            if (addIngredient(entityItems.get(i).getEntityItem().getItem())) {
                ItemStack hold = entityItems.get(i).getEntityItem().copy();
                entityItems.get(i).setDead();
                if (hold.getCount() > 1) {
                    hold.setCount(hold.getCount() - 1);
                    world.spawnEntity(new EntityItem(world, this.getPos().getX(), this.getPos().getY() + 0.5, this.getPos().getZ(), hold));
                }
            }
        }
    }

    private boolean isLit(){
      if (world.isRemote) return false;
        return  world.getBlockState(this.getPos().down()).getBlock() == Blocks.FIRE
                || world.getBlockState(this.getPos().down(2)).getBlock() == Blocks.FIRE && !world.getBlockState(this.getPos().down()).isFullBlock();
    }

    private int timeToIncrease(){
        return 180;
    }

    private int timeToDecrease(){
        return 180;
    }

    public boolean addIngredient(Item item) {
        if (isIngredient(item) && ingredientCount < 9 && !world.isRemote) {
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.id == getPotionId(item)) {
                    ingredient.amount++;
                    ingredientCount++;
                    return true;
                }
            }
            ingredientList.add(new Ingredient(getPotionId(item), 1, 22, getNewIngredientTime()));
            ingredientCount++;
            return true;
        }
        return false;
    }

    public int getTemperature(){
        return temperature;
    }

    private int getNewIngredientTime(){
        return 0;
    }

    private class Ingredient {
        int id;
        int amount;
        int temperature;
        int time;

        public Ingredient(int id, int amount, int temperature, int time)
        {
            this.id = id;
            this.amount = amount;
            this.temperature = temperature;
            this.time = time;
        }
    }
}