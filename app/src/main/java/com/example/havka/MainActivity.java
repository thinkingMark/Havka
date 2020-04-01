package com.example.havka;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    MealModel firstMeal = new MealModel("BORSHT",
            "Borsch is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color. Nice dish.",
            "0.5$",
            "1 H",
            "1 L",
            R.drawable.borsh);

    MealModel secondMeal = new MealModel("VARENYKY",
            "Ukrainian dish made of boiled dough with diverse fillings, such as meat,vegetables, fruits, cheese etc. These Ukrainian dumplings can be the main ",
            "0.8$",
            "2 h",
            "1 kg",
            R.drawable.varenyky);

    MealModel thirdMeal = new MealModel("UZVAR",
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give the drink an exotic flavor.",
            "0.5$",
            "20 m",
            "1 l",
            R.drawable.uzvar);

    MealModel fouthMeal = new MealModel("SIRNIKS",
            "Fried Eastern Slavic quark pancakes, garnished with sour cream, varenye, jam, honey or apple sauce. The cheese mixture may contain raisins for extra flavour. Nice dish",
            "3.5$",
            "40 m",
            "1 kg",
            R.drawable.sirniks);

    Button buttonFirstCourse;
    Button buttonSecondCourse;
    Button buttonDrinks;
    Button buttonDesserts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.meals);

        // Переключатель сторінок
        buttonFirstCourse = findViewById(R.id.firstcourse_button);
        buttonSecondCourse = findViewById(R.id.secondcourse_button);
        buttonDrinks = findViewById(R.id.drinks_button);
        buttonDesserts = findViewById(R.id.desserts_button);

        final Intent intent = new Intent(getApplicationContext(),SortedMeals.class);
        buttonFirstCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       intent.putExtra("meal","0");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                    }
                }
        );
        buttonSecondCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("meal","1");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                    }
                }
        );
        buttonDrinks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("meal","2");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                    }
                }
        );
        buttonDesserts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("meal","3");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                    }
                }
        );
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.favourite:
                        startActivity(new Intent(getApplicationContext(),Favourite.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.meals:

                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),Search.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
