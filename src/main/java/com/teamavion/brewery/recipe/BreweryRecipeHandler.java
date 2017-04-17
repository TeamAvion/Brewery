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

    public static short getPotionGrade(int id, int amount, int time, int averageTempreture, int marginOfError, boolean gradeIncreaseModifier, boolean gradeDecreaseModifer, int liquidMB){
        char tempGrade;
        char concentration = 'A';
        char grade;
        if(Math.abs(averageTempreture-Reference.EFFECT_TEMPRETURES[id]) < marginOfError*5)
            tempGrade = 'A';
        else if(Math.abs(averageTempreture-Reference.EFFECT_TEMPRETURES[id]) < marginOfError*5*2)
            tempGrade = 'B';
        else if(Math.abs(averageTempreture-Reference.EFFECT_TEMPRETURES[id]) < marginOfError*5*2+5)
            tempGrade = 'C';
        else
            tempGrade = 'D';
        if(getAverageGrade(tempGrade,concentration) > 'D')
            grade = 'D';
        else
            grade = getAverageGrade(tempGrade,concentration);
        if(grade != 'A' && gradeIncreaseModifier)
            grade = (char)((short)grade - 1);
        if(grade != 'D' && gradeDecreaseModifer)
            grade = (char)((short)grade + 1);
        return (short)grade;
    }

    private static char getAverageGrade(char tempGrade, char timeGrade) {
        if(((short)tempGrade + 1) == (short)timeGrade)
            return timeGrade;
        if(((short)timeGrade + 1) == (short)tempGrade)
            return tempGrade;
        else
            return (char)(((short)tempGrade +(short)timeGrade)/2);
    }
}
