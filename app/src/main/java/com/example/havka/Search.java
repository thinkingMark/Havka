package com.example.havka;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class Search extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    CustomAdapter customAdapter;
    IngredientsForSearch ingredientsForSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Верхня панель
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.search);

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

                    return true;
                }
                return false;
            }
        });

        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.meals) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        listView = (ListView)findViewById(R.id.list);
        searchIngredients();
        initList();
    }

    public void searchIngredients(){
        /**
         * @spinner це випадаюче вікно
         * з інгредієнтами
         */
        spinner = (Spinner) findViewById(R.id.spinner);
        ingredientsForSearch = new IngredientsForSearch();

        /**
         * Отримуємо елементи з класу
         * IngredientsForSearch
         */

        ArrayList<String> ingredients =
                new ArrayList<String>(Arrays.asList(ingredientsForSearch.getIngredients()));

        /**
         * @setAdapter метод який виводить
         * інгредіенти на екран
         */

        spinner.setAdapter(new ArrayAdapter<>(Search.this,
                android.R.layout.simple_spinner_dropdown_item, ingredients));

        customAdapter = new CustomAdapter();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // яка позиція такий елемент відкриється
                if( position >= 1 && position <= 12)
                    customAdapter.setMeals(0);
                else if(position >= 13 && position <= 17)
                    customAdapter.setMeals(1);
                else if(position >= 18 && position <= 22)
                    customAdapter.setMeals(2);
                else if(position >= 23 && position <= 30)
                    customAdapter.setMeals(3);
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    class CustomAdapter extends BaseAdapter{
        RatingBar mRatingBar;
        Button mButton;
        TextView mMealTitle;
        TextView mMealDescription;
        TextView mMealPrice;
        TextView mMealTime;
        TextView mMealCapacity;
        ImageView mMealImage;

        @Override
        public int getCount() { return 1; }

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.some_meal,null);

            mRatingBar = (RatingBar)view.findViewById(R.id.ratingBar);
            mButton = (Button)view.findViewById(R.id.meal_button);
            mMealTitle = (TextView)view.findViewById(R.id.meal_title);
            mMealDescription = (TextView)view.findViewById(R.id.meal_description);
            mMealPrice = (TextView)view.findViewById(R.id.meal_price);
            mMealTime = (TextView)view.findViewById(R.id.meal_time);
            mMealCapacity = (TextView)view.findViewById(R.id.meal_capacity);
            mMealImage = (ImageView)view.findViewById(R.id.meal_image);

            return view;
        }

        public void setMeals(final int i){

            mMealTitle.setText(Meals.meals[i].getMealTitle());
            mMealDescription.setText(Meals.meals[i].getMealDescription());
            mMealPrice.setText(Meals.meals[i].getMealPrice());
            mMealTime.setText(Meals.meals[i].getMealTime());
            mMealCapacity.setText(Meals.meals[i].getMealCapacity());
            mMealImage.setImageResource(Meals.meals[i].getMealImages());
            if(Meals.meals[i].isFavourite)
                mRatingBar.setRating(1);
            else mRatingBar.setRating(0);

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
                    }


                }
            });

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Information.class));
                    overridePendingTransition(0,0);
                }
            });
        }
    }
    public void initList(){
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }
}
