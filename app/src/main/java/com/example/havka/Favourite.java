package com.example.havka;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
/**
 *  Cторінка "Улюблені страви" нашої програми, за архітектурою програми №2. Містить улюблені страви, вибрані при натиску на зірочку.
 *  @version 1.0
 */
public class Favourite extends AppCompatActivity {
    /**
     * Список улюблених страв, що змінюється адаптером.
     */
    ListView listView;
    /**
     * Адаптер, що заповнює список.
     */
    CustomAdapter customAdapter;
    View view;
    /**
     *  Нижнє поле навігації.
     *  Перехід між сторінками №1, №2, №3
     *  По стандарту вибрана сторінкка №2
     */
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    String SAVED_RATING = "Rating saved";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourite);
        /**
         *  Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
         */
        bottomNavigationView = findViewById(R.id.bootom_navigation);

        /**
         *  Спочатку вибране "Meals",бо це головна сторінка
         */
        bottomNavigationView.setSelectedItemId(R.id.favourite);

        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        // Переключатель сторінок
        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №1, №2, №3
         *  По стандарту вибрана сторінкка №2
         */
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

        DataBaseHelper dataBaseHelper = new DataBaseHelper(Favourite.this);
        Meals.favouriteList = dataBaseHelper.getAll();
        dataBaseHelper.close();

        listView = (ListView)findViewById(R.id.listView);
        initList();
    }

    /**
     * Метод для оновлення списку улюблених страви.
     * За рахунок оновлення адаптера оновлюється список.
     */
    public void initList(){
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }

    /**
     * Адаптер, що заповнює список.
     */
    class CustomAdapter extends BaseAdapter {

        /**
         * Метод відповідальний за величину списку.
         * @return величина списку
         * В нашому випадку повертає величину списку улюблених страв.
         */
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

        /**
         * Метод, що привязує елемент списку до some_meal.xml
         * Встановлює поля some_meal.xml деякими стравами.
         * В нашому випадку встановлє страви з favouriteList - списку улюблених страв
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
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

            if (Meals.favouriteList.get(position).isFavourite){
                mRatingBar.setRating(1);
                saveRating(mRatingBar.getNumStars());
            } else {
                mRatingBar.setRating(0);
                saveRating(mRatingBar.getNumStars());
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(Favourite.this);

            for (int j = 0; j < dataBaseHelper.getAll().size(); j++){
                if (dataBaseHelper.getAll().get(j).getMealTitle().equals(Meals.favouriteList.get(position).getMealTitle())){
                    mRatingBar.setRating(loadRating());
                }
            }

            dataBaseHelper.close();

            /**
            * При натиску на зірочку видаляється\додається елемент з\в списку улюблених страв.
             ** Після кожного натиску виконує оновлення списку - initList
            */
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (rating == 1) {
                        Meals.meals[position].setFavourite(true);
                        Toast.makeText(Favourite.this, "Meal add to Favourite", Toast.LENGTH_SHORT).show();
                    } else {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(Favourite.this);
                        dataBaseHelper.removeOne(Meals.favouriteList.get(position).getMealTitle());
                        Meals.meals[position].setFavourite(false);
                        Meals.favouriteList.remove(position);
                        dataBaseHelper.close();
                        initList();
                    }


                }
            });
            /**
             *  При натиску на кнопку переходить на сторінку №5 за архітектурою програми.
             *  Сторінка містить інформацію про страву.
             */
            final Intent intentInformation = new Intent(getApplicationContext(), Information.class);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (Meals.favouriteList.get(position).getMealTitle()){
                        case "BORSCHT":
                            intentInformation.putExtra("meal", 0);
                            break;
                        case "VARENYKY":
                            intentInformation.putExtra("meal", 1);
                            break;
                        case "UZVAR":
                            intentInformation.putExtra("meal", 2);
                            break;
                        case "SIRNIKS":
                            intentInformation.putExtra("meal", 3);
                            break;
                        default:
                            break;
                    }
                    startActivity(intentInformation);
                    overridePendingTransition(0, 0);

                }
            });
                return view;
        }
    }

    private void saveRating(int value){
        sharedPreferences = getSharedPreferences("Rating meal's", MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putInt(SAVED_RATING, value);
        ed.commit();
    }

    private int loadRating(){
        sharedPreferences = getSharedPreferences("Rating meal's", MODE_PRIVATE);
        int rating = sharedPreferences.getInt(SAVED_RATING, -1);
        return rating;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
