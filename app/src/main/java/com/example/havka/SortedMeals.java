package com.example.havka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SortedMeals extends AppCompatActivity {

    ListView listView;
    String mMealTitle[] = {"BORSHT","VARENYKY","UZVAR","SIRNIKS"};
    String mMealDescription[] = {"Borsch is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color.",
            "Ukrainian dish made of boiled dough with diverse fillings, such as meat,vegetables, fruits, cheese etc. These Ukrainian dumplings can be the main course as well as the dessert.",
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give the drink an exotic flavor.",
            "Fried Eastern Slavic quark pancakes, garnished with sour cream, varenye, jam, honey or apple sauce. The cheese mixture may contain raisins for extra flavour."};
    String mMealPrice[] = {"10$","9$","16$","7$"};
    String mMealTime[] = {"1 H","2 h","2.5h","0.5H"};
    int mMealImages[] = {R.drawable.uzvar,R.drawable.varenyky,R.drawable.uzvar,R.drawable.sirniks};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_meals);

        listView = findViewById(R.id.listView);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Information.class));
                        overridePendingTransition(0,0);
                    }
                }
        );
        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.meals);

        // Переключатель сторінок
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.favourite:
                        startActivity(new Intent(getApplicationContext(),Favourite.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.meals:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);

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

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rMealTitle;
        String rMealDescription;
        String rMealPrice;
        String rMealTime;
        int rMealImage;

        MyAdapter(Context c, String title[], String description[], String price[], String time[],int images[]){
            super(c,R.layout.m){

            };

        }

    }
}
