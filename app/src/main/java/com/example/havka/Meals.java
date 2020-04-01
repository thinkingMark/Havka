package com.example.havka;

import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Meals {
    static public MealModel firstMeal = new MealModel("BORSHT",
            "Borsch is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color. Nice dish.",
            "0.5$",
            "1 H",
            "1 L",
            R.drawable.borsh);

    static public MealModel secondMeal = new MealModel("VARENYKY",
            "Ukrainian dish made of boiled dough with diverse fillings, such as meat,vegetables, fruits, cheese etc. These Ukrainian dumplings can be the main course as well as the dessert.",
            "0.8$",
            "2 h",
            "1 kg",
            R.drawable.varenyky);

    static public MealModel thirdMeal = new MealModel("UZVAR",
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give the drink an exotic flavor.",
            "0.5$",
            "20 m",
            "1 l",
            R.drawable.uzvar);

    static public MealModel fouthMeal = new MealModel("SIRNIKS",
            "Fried Eastern Slavic quark pancakes, garnished with sour cream, varenye, jam, honey or apple sauce. The cheese mixture may contain raisins for extra flavour. Nice dish",
            "3.5$",
            "40 m",
            "1 kg",
            R.drawable.sirniks);
    static public MealModel[] meals = {firstMeal, secondMeal, thirdMeal, fouthMeal};
    static List<MealModel> favouriteList = new LinkedList<MealModel>();

}
