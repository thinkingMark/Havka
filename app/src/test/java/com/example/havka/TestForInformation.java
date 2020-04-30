package com.example.havka;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForInformation {

    private Information information = new Information();
    private MealModel mealsModel;

    @Test
    public void checkInformationAboutMeal() {
        assertNotNull(information.getInformation());
    }

    @Test
    public void checkImageAboutMeal(){
        assertNotNull(information.Id);
    }

    @Test
    public void checkMealFile(){
        mealsModel = Meals.meals[0];
        assertEquals("Borsch.txt", mealsModel.getFileName());
        mealsModel = Meals.meals[1];
        assertEquals("Varenyky.txt", mealsModel.getFileName());
        mealsModel = Meals.meals[2];
        assertEquals("Uzvar.txt", mealsModel.getFileName());
        mealsModel = Meals.meals[3];
        assertEquals("Sirniks.txt", mealsModel.getFileName());

    }
}
