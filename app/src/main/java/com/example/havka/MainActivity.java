package com.example.havka;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

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

        final Intent intentSorted = new Intent(getApplicationContext(),SortedMeals.class);
        buttonFirstCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentSorted.putExtra("meal","0");
                        startActivity(intentSorted);
                        overridePendingTransition(0,0);

                    }
                }
        );
        buttonSecondCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentSorted.putExtra("meal","1");
                        startActivity(intentSorted);
                        overridePendingTransition(0,0);

                    }
                }
        );
        buttonDrinks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentSorted.putExtra("meal","2");
                        startActivity(intentSorted);
                        overridePendingTransition(0,0);

                    }
                }
        );
        buttonDesserts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentSorted.putExtra("meal","3");
                        startActivity(intentSorted);
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

    int[] buttonsId = {
            R.id.firstcourse_button,
            R.id.secondcourse_button,
            R.id.drinks_button,
            R.id.desserts_button
    };
}
