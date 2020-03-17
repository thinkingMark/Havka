package com.example.havka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.inforamation);

        // Переключатель сторінок
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ingridients:
                        startActivity(new Intent(getApplicationContext(),Ingridients.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.inforamation:

                        return true;
                    case R.id.instructions:
                        startActivity(new Intent(getApplicationContext(),Instructions.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }
}
