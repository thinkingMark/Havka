package com.example.havka;

import java.util.ArrayList;
import java.util.List;

/**
 *  Тимчасова база даних. Містить страви
 */
public class Meals {
    static String description;
    static String[] firstMealInstructions =  {
            "Crumble the sausage (if using) into \n a skillet over medium-high heat.",
            "Cook and stir until no longer pink. \n Remove from the heat and set aside.",
            "Fill a large pot halfway with water \n (about 2 quarts), and bring to a boil. \n Add the sausage, and cover the pot.",
            "Return to a boil. Add the beets, and \n cook until they have lost their color.",
            "Add the carrots and potatoes, and \n cook until tender, about 15 minutes.",
            "Add the cabbage, and the can of \n diced tomatoes.",
            "Heat the oil in a skillet over medium \n heat.",
            "Add the onion,and cook until tender.\n Stir in the tomato paste and water \n until well blended. Transfer to the pot.",
            "Add the raw garlic to the soup,\n cover and turn off the heat.",
            "Let stand for 5 minutes.Taste, and \n season with salt, pepper and sugar."};
    static String[] secondMealInstructions =  {
            "Wash, peel and cut the potatoes into big pieces.Cook the potatoes in the \n salted water for 15-20 minutes. ",
            "Peel and dice the onion.Heat the \n vegetable oil in the frying pan; brown \nthe onion.",
            "Pour off the water from boiled potatoes.",
            "Add the 1/3 of fried onion, butter, \n pepper,salt and stir well. Set the \nstuffing aside to cool.",
            "Combine the kefir, soda, salt\n and stir well.Stir the flour \nthrough and make dough.",
            "The dough should not be stick but \nstiff and fully combined.",
            "Split the dough into two rolls and \n cut them into little pieces. Shape thin \n tablets with rolling pin. Dip each side \n of the table in the flour.",
            "Spoon the stuffing in the middle of \n the tablet and stick edges together.",
            "Put the finished vareniki on the flat \n working surface and powder with flour.",
            "Cook the vareniki in salted water for\n 3-4 minutes.",
            "Season the cooked vereniki with the \n remaining browned onion and serve \n with sour cream."};
    static String[] thirdMealInstructions =  {
            "Prepare the ingredients. Wash the \ndried fruit.Carefully make sure that \nthey do not have sand or soil residues.",
            "Put all the dried fruits (apples, \npears and plums) in a large pot for \ncooking, fill it with cold water, cover and \n place on to the burner,  on a moderat fire \n and bring to simmer to boil.",
            "Simmer for 10-15 minutes, then taste \nand add sugar(or honey), if necessary. \nThe amount of sugar (or honey) may \nvary. It all depends on the \nsweetness of fruit.",
            "If the fruit sweet enough, you can do \nwithout sugar. Compote should not be \ncloyingly sweet - the taste of fruits themselves\n must dominate.",
            "Remove the pan from the burner and\n let uzvar cooled to room temperature, \nwhich may take several hours,during \nwhich the drink is brewed.",
            "Drain uzvar, separating the liquid \n from the pulp of fruit. If you see that \n the liquid is too dense, add a little \nboiling water to dilute the compote.",
            "Pour the compote into a jug. Serve \nchilled or at room temperature. It may well be\n stored in the refrigerator for several\n days."};
    static String[] fourthMealInstructions =  {
            "In a small bowl, lay 400 g of cottage\n cheese, 1 egg, 3 tbsp. l sugar. It is best\n to use homemade cottage cheese \npurchased at the bazaar.Cottage cheese \nshould not be acidic and excessively wet.",
            "Mix all ingredients until smooth. You\n can try the mixture while stirring and \nadd a little sugar if the cheesecakes \ndo not seem sweet enough to you.",
            "Add to the cottage cheese 3 tbsp.l \nflour. If the cottage cheese is very wet, \n add 1 tbsp. l more flour. The main thing here is \n to maintain a balance so that cheesecakes \nare not too dense.",
            "Form small balls from the curd mass\n then flatten them a little with your hands. \nSo that the curd mass does not stick \nto \nyour hands, you can lubricate them \nwith a few drops of sunflower oil.",
            "Roll the cottage cheese pancakes in\n flour so that they better keep their\n shape and fry with a beautiful golden crust.",
            "Fry the cheesecakes in a well-heated\n skillet with  3-4 tbsp. l sunflower oil on \neach side  until golden brown.Fry the \ncheesecakes over medium heat,so you get the\n optimal frying.",
            "Put the finished cheesecakes on a\n plate covered with a paper towel so \nthat excess fat is absorbed into it.\n Serve cheese cakes with jam, jam or\n sour cream. Or you can just sprinkle\n them with powdered sugar through a \nfine sieve."};


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
            "Borscht is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color.",
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
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give drink an exotic flavor.",
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
    static List<MealModel> favouriteList = new ArrayList<MealModel>();
    static ArrayList<MealModel> findedList = new ArrayList<>();
    static ArrayList<String> choosedIngridients = new ArrayList<>();

}
