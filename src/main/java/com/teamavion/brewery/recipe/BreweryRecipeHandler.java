package com.teamavion.brewery.recipe;

import com.sun.istack.internal.NotNull;
import com.teamavion.brewery.Reference;
import net.minecraft.item.Item;

/**
 * Created by TjKenMate on 4/15/2017XD
 */
public class BreweryRecipeHandler {
    public static boolean isIngredient(Item input){
        for(Item a: Reference.EFFECT_ITEMS)
            if(a != null && a.getUnlocalizedName().equals(input.getUnlocalizedName()))
                return true;
        return false;
    }
    @NotNull
    public static int getPotionId(Item input) {
        for (int i = 0; i < Reference.EFFECT_ITEMS.length; i++){
            if(Reference.EFFECT_ITEMS[i] != null && Reference.EFFECT_ITEMS[i].getUnlocalizedName().equals(input.getUnlocalizedName()))
                return i;
        }
        return 0;
    }

    public static char getPotionGrade(int id, int amount, int time, int averageTempreture, int marginOfError,
                                      boolean gradeIncreaseModifier, boolean gradeDecreaseModifer){
        return 'A';}
}
