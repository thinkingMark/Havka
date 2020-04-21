package com.example.havka;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
/**
 *
 *  Cторінка відсортованих страв. За архітектурою програми №4.
 *  Містить список страв. Кожен елемент містить міімальну інформацію про страву*
 *   та кнопку для переходу для детального опису страви.
 *   @version 1.0
 *
 */
public class SortedMeals extends AppCompatActivity {

    int i;
    ListView listView;
    CustomAdapter customAdapter;
    EditText editText;
    Intent favouritePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle argumentsFirstPage = getIntent().getExtras();
        String s = argumentsFirstPage.get("meal").toString();
        i = Integer.parseInt(s);
        setContentView(R.layout.activity_sorted_meals);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);
        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.meals);
        // Переключатель сторінок
        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №1, №2, №3
         *  По стандарту вибрана сторінкка №4
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
        listView = (ListView)findViewById(R.id.listView);
        initList();
         editText = (EditText)findViewById(R.id.editText);


    }

    /**
     * Адаптер, що заповнює список.
     */
    class CustomAdapter extends BaseAdapter{

        /**
         * Метод відповідальний за величину списку.
         * @return величина списку
         * В нашому випадку повертає 1. Кількість страв повзоляє тільки так
         */
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return super.getAutofillOptions();
        }

        /**
         * Метод, що привязує елемент списку до some_meal.xml
         * Встановлює поля some_meal.xml деякими стравами.
         * В нашому випадку встановлє страви з масиву страв Meals.meals
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.some_meal,null);

            RatingBar mRatingBar = (RatingBar)view.findViewById(R.id.ratingBar);
            Button mButton = (Button)view.findViewById(R.id.meal_button);
            TextView mMealTitle = (TextView)view.findViewById(R.id.meal_title);
            TextView mMealDescription = (TextView)view.findViewById(R.id.meal_description);
            TextView mMealPrice = (TextView)view.findViewById(R.id.meal_price);
            TextView mMealTime = (TextView)view.findViewById(R.id.meal_time);
            TextView mMealCapacity = (TextView)view.findViewById(R.id.meal_capacity);
            ImageView mMealImage = (ImageView)view.findViewById(R.id.meal_image);


            mMealTitle.setText(Meals.meals[i].getMealTitle());
            mMealDescription.setText(Meals.meals[i].getMealDescription());
            mMealPrice.setText(Meals.meals[i].getMealPrice());
            mMealTime.setText(Meals.meals[i].getMealTime());
            mMealCapacity.setText(Meals.meals[i].getMealCapacity());
            mMealImage.setImageResource(Meals.meals[i].getMealImages());
            if(Meals.meals[i].isFavourite)
                mRatingBar.setRating(1);
            else mRatingBar.setRating(0);

            /**
             *  При натиску на зірочку видаляється\додається елемент з\в списку улюблених страв.
             *  Виконуються зміни на сторінці №5
             */

            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(rating == 1){
                        Meals.meals[i].setFavourite(true);
                        Meals.favouriteList.add(Meals.meals[i]);
                    }
                    else{
                        Meals.meals[i].setFavourite(false);
                        Meals.favouriteList.remove(Meals.meals[i]);
                        initList();
                    }
                }
            });
            /**
             *  При натиску на кнопку переходить на сторінку №5 за архітектурою програми.
             *  Сторінка містить інформацію про страву.
             */
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Information.class));
                    overridePendingTransition(0,0);
                }
            });

            return view;
        }



    }

    /**
     * Метод для оновлення списку страв.
     * За рахунок оновлення адаптера оновлюється список.
     */
    public void initList(){
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

}
