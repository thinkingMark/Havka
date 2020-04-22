package com.example.havka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Тимчасова база даних. Містить страви
 */
public class Meals {
    static String[] firstMealIngridients =  {"Sausage", "Beet",
            "Carrot", "Potato",
            "Oil", "Onion",
            "Paste", "Cabbage",
            "Tomato", "Garlic",
            "Pepper", "Parsley"};
    static public MealModel firstMeal = new MealModel("BORSHT",
            "Borsch is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color. Nice dish.",
            "0.5$",
            "1 H",
            "1 L",
            R.drawable.borsh,
            firstMealIngridients);

    static String[] secondMealIngridients =  {"Potato", "Flour",
            "Sour cream", "Onion",
            "Mushrooms"};
    static public MealModel secondMeal = new MealModel("VARENYKY",
            "Ukrainian dish made of boiled dough with diverse fillings, such as meat,vegetables, fruits, cheese etc. These Ukrainian dumplings can be the main course as well as the dessert.",
            "0.8$",
            "2 h",
            "1 kg",
            R.drawable.varenyky,
            secondMealIngridients);

    static String[] thirdMealIngridients =  {"Apples", "Pears",
            "Raisins", "Prunes",
            "Honey"};
    static public MealModel thirdMeal = new MealModel("UZVAR",
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give the drink an exotic flavor.",
            "0.5$",
            "20 m",
            "1 l",
            R.drawable.uzvar,
            thirdMealIngridients);

    static String[] fourthMealIngridients =  {"Farmers cheese", "Eggs",
            "Flour", "Sugar",
            "Soda", "Vinegar",
            "Raisins", "Olive oil"};
    static public MealModel fouthMeal = new MealModel("SIRNIKS",
            "Fried Eastern Slavic quark pancakes, garnished with sour cream, varenye, jam, honey or apple sauce. The cheese mixture may contain raisins for extra flavour. Nice dish",
            "3.5$",
            "40 m",
            "1 kg",
            R.drawable.sirniks,
            fourthMealIngridients);
    static public MealModel[] meals = {firstMeal, secondMeal, thirdMeal, fouthMeal};
    static List<MealModel> favouriteList = new LinkedList<MealModel>();
    static ArrayList<MealModel> findedList = new ArrayList<>();
    static ArrayList<String> choosedIngridients = new ArrayList<>();

}
