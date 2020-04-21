package com.example.havka;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 *
 *  Головна сторінка нашої програми. За архітектурою програми №1.
 *  Містить кнопки для вибору категорії страв.
 *  @version 1.0
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
     *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
     *  В нашому випадку Перші страви: Борщ...
     */
    Button buttonFirstCourse;
    /**
     *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
     *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
     *  В нашому випадку Другі страви: Вареники...
     */
    Button buttonSecondCourse;

    /**
     *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
     *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
     *  В нашому випадку Напитки: Узвар...
     */
    Button buttonDrinks;

    /**
     *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
     *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
     *  В нашому випадку Десерти : Сирники...
     */
    Button buttonDesserts;

    /**
     *  Нижнє поле навігації.
     *  Перехід між сторінками №1, №2, №3
     *  По стандарту вибрана сторінкка №1
     */
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bootom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.meals);
        buttonFirstCourse = findViewById(R.id.firstcourse_button);
        buttonSecondCourse = findViewById(R.id.secondcourse_button);
        buttonDrinks = findViewById(R.id.drinks_button);
        buttonDesserts = findViewById(R.id.desserts_button);

        final Intent intent = new Intent(getApplicationContext(),SortedMeals.class);
        /**
         *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
         *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
         *  В нашому випадку Перші страви: Борщ...
         */
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
        /**
         *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
         *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
         *  В нашому випадку Другі страви: Вареники...
         */
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
        /**
         *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
         *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
         *  В нашому випадку Напитки: Узвар...
         */
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
        /**
         *  При натиску на кнопку, відкривається сторінка №4, з стравами за категорією.
         *  Передається на наступну сторінку ідентифікатор, за яким певна страва висвітлюється.
         *  В нашому випадку Десерти : Сирники...
         */
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
        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №1, №2, №3
         *  По стандарту вибрана сторінкка №1
         */
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
