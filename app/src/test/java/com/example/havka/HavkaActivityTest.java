package com.example.havka;



import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HavkaActivityTest {

    private MainActivity mainActivity = new MainActivity();
    private Meals mealsTesting = new Meals();
    private MealModel mealModel = Meals.meals[0];
    private Information informationAboutMeal = new Information();
    private SortedMeals sortedMeals = new SortedMeals();

    /*
    Перевіряє справність кнопок
     */
    @Test
    public void checkButtonsId() {
        assertNotNull(mainActivity.buttonsId);
    }

    /*
    Перевіряє чи правильно зроблена модель їжі
     */

    @Test
    public void testMealModel() {
        assertEquals("BORSHT", mealModel.getMealTitle());
        assertEquals(
                "Borsch is a sour soup common " +
                         "in Eastern Europe and Northern Asia." +
                         "Made with beetroots as one of the main ingredients, " +
                        "which give the dish its distincti-ve red color. " +
                        "Nice dish.", mealModel.getMealDescription());
        assertEquals("0.5$", mealModel.MealPrice);
        assertEquals("1 H", mealModel.getMealTime());
        assertEquals("1 L", mealModel.getMealCapacity());
        assertNotNull(R.drawable.borsh);
    }



    @Test
    public void checkInformationAboutMeal() {
        assertNotNull(informationAboutMeal.getInformation());
    }

    @Test
    public void checkListView() {
        assertEquals(sortedMeals.listView, sortedMeals.customAdapter);
    }

}
