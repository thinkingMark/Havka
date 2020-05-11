package com.example.havka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Тимчасова база даних. Містить страви
 */
public class Meals {
    static String description;

    static String[] firstMealIngridients =  {
            "Sausage", "Beet",
            "Carrot", "Potato",
            "Oil", "Onion",
            "Paste", "Cabbage",
            "Tomato", "Garlic",
            "Pepper", "Parsley"};
    static int[] defaultIngredientsBorsch = { // інгдедієнти
            100, 150, // sausage, beet
            70,  200,  // carrot, potato,
            50,   70,   // oil, onion
            100, 100,// paste, cabbage,
            100,  20,//  tomato, garlic,
            20,   30  //  pepper, parsley
    };
    static public MealModel firstMeal = new MealModel("BORSCHT",
            "Borscht is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color. Nice dish.",
            "0.5$",
            "1 H",
            "1 L",
            R.drawable.borsh,
            firstMealIngridients,
            "Borscht.txt");

    static String[] secondMealIngridients =  {
            "Potato", "Flour",
            "Sour cream", "Onion",
            "Mushrooms"};
    static int[] defaultIngredientsVarenyky = {
            300, 300, // potato, flour
            60, 30,   // sour cream, onion
            50,       // mushrooms
    };
    static public MealModel secondMeal = new MealModel("VARENYKY",
            "Ukrainian dish made of boiled dough with diverse fillings, such as meat,vegetables, fruits, cheese etc. These Ukrainian dumplings can be the main course as well as the dessert.",
            "0.8$",
            "2 h",
            "1 kg",
            R.drawable.varenyky,
            secondMealIngridients,
            "Varenyky.txt");

    static String[] thirdMealIngridients =  {
            "Apples", "Pears",
            "Raisins", "Prunes",
            "Honey"};
    static int[] defaultIngredientsUzvar = {
            150, 100, // apples, pears
            50, 30,   // raisins, prunes
            30,       // honey
    };
    static public MealModel thirdMeal = new MealModel("UZVAR",
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give the drink an exotic flavor.",
            "0.5$",
            "20 m",
            "1 l",
            R.drawable.uzvar,
            thirdMealIngridients,
            "Uzvar.txt");

    static String[] fourthMealIngridients =  {
            "Farmers cheese", "Eggs",
            "Flour", "Sugar",
            "Soda", "Vinegar",
            "Raisins", "Olive oil"};
    static int[] defaultIngredientsSirniks = {
            250, 100, // farmers cheese, eggs
            100, 80,  // sugar, flour
            30,  20,  // soda, vinegar
            40, 40    // raisins, olive oil
    };
    static public MealModel fouthMeal = new MealModel("SIRNIKS",
            "Fried Eastern Slavic quark pancakes, garnished with sour cream, varenye, jam, honey or apple sauce. The cheese mixture may contain raisins for extra flavour. Nice dish",
            "3.5$",
            "40 m",
            "1 kg",
            R.drawable.sirniks,
            fourthMealIngridients,
            "Sirniks.txt");

    static public MealModel[] meals = {firstMeal, secondMeal, thirdMeal, fouthMeal};
    static List<MealModel> favouriteList = new LinkedList<MealModel>();
    static ArrayList<MealModel> findedList = new ArrayList<>();
    static ArrayList<String> choosedIngridients = new ArrayList<>();

}
