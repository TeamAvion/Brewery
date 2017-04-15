package com.teamavion.brewery.recipe;

import com.sun.istack.internal.NotNull;
import net.minecraft.item.Item;

/**
 * Created by TjKenMate on 4/15/2017XD
 */
public class BreweryRecipeHandler {
    public static boolean isIngredient(Item input){return true;}
    @NotNull
    public static int getPotionId(Item input){return 0;}
    public static char getPotionGrade(int id, int time, int averageTempreture, int marginOfError,
                                      boolean gradeIncreaseModifier, boolean gradeDecreaseModifer){
        return 'A';}
}
