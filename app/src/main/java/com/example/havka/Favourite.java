package com.example.havka;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Favourite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.favourite);

        // Переключатель сторінок
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.favourite:
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
