package com.example.havka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SortedMeals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_meals);



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
}
