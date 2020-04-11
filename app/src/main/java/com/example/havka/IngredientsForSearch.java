package com.example.havka;


public class IngredientsForSearch {

    private String[] ingredients;

    public IngredientsForSearch(){
        ingredients = new String[] {
                // First course
                "Select an ingredients",
                "Sausage", "Beet",
                "Carrot", "Potato",
                "Oil", "Onion",
                "Paste", "Cabbage",
                "Tomato", "Garlic",
                "Pepper", "Parsley",
                // Second course
                "Potato", "Flour",
                "Sour cream", "Onions",
                "Mushrooms",
                // Beverages
                "Apples", "Pears",
                "Raisins", "Prunes",
                "Honey",
                // Deserts
                "Farmers cheese", "Eggs",
                "Flour", "Sugar",
                "Soda", "Vinegar",
                "Raisins", "Olive oil"
        };
    }

    public String[] getIngredients(){
        return ingredients;
    }
}
