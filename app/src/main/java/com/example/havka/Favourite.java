package com.example.havka;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Favourite extends AppCompatActivity {

    ListView listView;
    int numberMeal;
    CustomAdapter customAdapter;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Bundle argumentsFourthPage = getIntent().getExtras();

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
        listView = (ListView)findViewById(R.id.listView);
        initList();
    }
    public void initList(){
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Meals.favouriteList.size();
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

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.some_meal, null);
            RatingBar mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            Button mButton = (Button) view.findViewById(R.id.meal_button);
            TextView mMealTitle = (TextView) view.findViewById(R.id.meal_title);
            TextView mMealDescription = (TextView) view.findViewById(R.id.meal_description);
            TextView mMealPrice = (TextView) view.findViewById(R.id.meal_price);
            TextView mMealTime = (TextView) view.findViewById(R.id.meal_time);
            TextView mMealCapacity = (TextView) view.findViewById(R.id.meal_capacity);
            ImageView mMealImage = (ImageView) view.findViewById(R.id.meal_image);


            mMealTitle.setText(Meals.favouriteList.get(position).getMealTitle());
            mMealDescription.setText(Meals.favouriteList.get(position).getMealDescription());
            mMealPrice.setText(Meals.favouriteList.get(position).getMealPrice());
            mMealTime.setText(Meals.favouriteList.get(position).getMealTime());
            mMealCapacity.setText(Meals.favouriteList.get(position).getMealCapacity());
            mMealImage.setImageResource(Meals.favouriteList.get(position).getMealImages());
            if (Meals.favouriteList.get(position).isFavourite)
                mRatingBar.setRating(1);
            else mRatingBar.setRating(0);


            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (rating == 1) {
                        Meals.meals[position].setFavourite(true);
                    } else {
                        Meals.meals[position].setFavourite(false);
                        Meals.favouriteList.remove(position);
                        initList();
                    }


                }
            });

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Information.class));
                    overridePendingTransition(0, 0);
                }
            });
                return view;
    }
    }
}
