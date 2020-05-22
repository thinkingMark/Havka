package com.example.havka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 *  Сторінка пошуку страв за інгрідієнтами. За архітектурою сторінка №3.
 *  Містить пошукове поле. При натиску на певний інгдрієнт висвітлюється страва.
 *  @version 0.0
 */
public class Search extends AppCompatActivity {

    /**
     * Випадаюче вікно інгрідієнтів
     */
    Spinner spinner;
    /**
     * Список, що оновлюється пілся вибору інгрідієнта
     */
    ListView listView;
    /**
     * Адаптер, що заповнює список.
     */
    CustomAdapter customAdapter;
    /**
     * Логіка пошуку інгрідієнтів.
     */
    IngredientsForSearch ingredientsForSearch;
    MealModel mealModel;
    SharedPreferences sharedPreferences;
    String SAVED_RATING = "Rating saved";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        /**
         * Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
         */

        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        /**
         * Спочатку вибране "Meals",бо це головна сторінка
         */

        bottomNavigationView.setSelectedItemId(R.id.search);
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №1, №2, №3
         *  По стандарту вибрана сторінкка №3
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
        findMeals();
        initList();
        initIngridientList();



//------------------------------------------------------------------------------------------------//
        spinner = (Spinner) findViewById(R.id.spinner);
        /**
         * @spinner це випадаюче вікно
         * з інгредієнтами
         */

        ingredientsForSearch = new IngredientsForSearch();

        /**
         * Отримуємо елементи з класу
         * IngredientsForSearch
         */

        final ArrayList<String> ingredients =
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

                Meals.choosedIngridients.add(ingredients.get(position));
                findMeals();
                initList();
                initIngridientList();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
//------------------------------------------------------------------------------------------------//
    class CustomAdapter extends BaseAdapter{
        RatingBar mRatingBar;
        Button mButton;
        TextView mMealTitle;
        TextView mMealDescription;
        TextView mMealPrice;
        TextView mMealTime;
        TextView mMealCapacity;
        ImageView mMealImage;

        /**
         * Метод відповідальний за величину списку.
         * @return величина списку
         * В нашому випадку величина найдених страв
         */
        @Override
        public int getCount() { return Meals.findedList.size(); }

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
         * В нашому випадку встановлє страви з /todo
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.some_meal, null);

            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            mButton = (Button) view.findViewById(R.id.meal_button);
            mMealTitle = (TextView) view.findViewById(R.id.meal_title);
            mMealDescription = (TextView) view.findViewById(R.id.meal_description);
            mMealPrice = (TextView) view.findViewById(R.id.meal_price);
            mMealTime = (TextView) view.findViewById(R.id.meal_time);
            mMealCapacity = (TextView) view.findViewById(R.id.meal_capacity);
            mMealImage = (ImageView) view.findViewById(R.id.meal_image);

            mMealTitle.setText(Meals.findedList.get(position).getMealTitle());
            mMealDescription.setText(Meals.findedList.get(position).getMealDescription());
            mMealPrice.setText(Meals.findedList.get(position).getMealPrice());
            mMealTime.setText(Meals.findedList.get(position).getMealTime());
            mMealCapacity.setText(Meals.findedList.get(position).getMealCapacity());
            mMealImage.setImageResource(Meals.findedList.get(position).getMealImages());

            mealModel = new MealModel(
                    Meals.findedList.get(position).getMealTitle(),
                    Meals.findedList.get(position).getMealDescription(),
                    Meals.findedList.get(position).getMealPrice(),
                    Meals.findedList.get(position).getMealTime(),
                    Meals.findedList.get(position).getMealCapacity(),
                    Meals.findedList.get(position).getMealImages()
            );

            if (Meals.findedList.get(position).isFavourite) {
                mRatingBar.setRating(1);
                saveRating(mRatingBar.getNumStars());
            } else {
                mRatingBar.setRating(0);
                saveRating(mRatingBar.getNumStars());
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(Search.this);

            for (int j = 0; j < dataBaseHelper.getAll().size(); j++){
                if (dataBaseHelper.getAll().get(j).getMealTitle().equals(Meals.findedList.get(position).getMealTitle())){
                    mRatingBar.setRating(loadRating());
                }
            }
            dataBaseHelper.close();

            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (rating == 1) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(Search.this);

                        boolean success = dataBaseHelper.addOne(mealModel);
                        Meals.favouriteList.addAll(dataBaseHelper.getAll());
                        dataBaseHelper.close();

                        Meals.meals[position].setFavourite(true);
                        Toast.makeText(Search.this, "Meal add to Favourite", Toast.LENGTH_SHORT).show();
                    } else {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(Search.this);
                        dataBaseHelper.removeOne(Meals.favouriteList.get(position).getMealTitle());
                        Meals.meals[position].setFavourite(false);
                        Meals.favouriteList.remove(position);
                        dataBaseHelper.close();
                        initList();
                    }
                }
            });

            final Intent intentInformation = new Intent(getApplicationContext(), Information.class);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (Meals.findedList.get(position).getMealTitle()){
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

    /**
     * Метод для оновлення списку найдених страви.
     * За рахунок оновлення адаптера оновлюється список.
     */
    public void initList(){
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }
//------------------------------------------------------------------------------------------------//

    /**
     * Адаптер, що заповняє список кнопок-інгрідієнтів
     */
    class IngridientsRecyclerViewAdapter
            extends RecyclerView.Adapter<IngridientsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mIngridients = new ArrayList<>();

    Context mContext;

    public IngridientsRecyclerViewAdapter(ArrayList<String> mIngridients, Context mContext) {
        this.mIngridients = mIngridients;
        this.mContext = mContext;
        for(int i=0; i<mIngridients.size(); i++){
            if(mIngridients.get(i).equals("Select ingridients")){
                mIngridients.remove(i);
        }   }

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.some_ingridient, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mIngridientButton.setText(mIngridients.get(position) + " ×");
        holder.mIngridientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meals.choosedIngridients.remove(position);
                notifyDataSetChanged();
                findMeals();
                initList();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIngridients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button mIngridientButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIngridientButton = itemView.findViewById(R.id.ingridient_button);
        }
    }
}

    /**
     * Оновлення списку кнопок-інгрідієнтів
     */
    public void initIngridientList(){

        RecyclerView recyclerView = findViewById(R.id.ingridient_list);
        IngridientsRecyclerViewAdapter adapter = new IngridientsRecyclerViewAdapter(Meals.choosedIngridients, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
    }
//------------------------------------------------------------------------------------------------//

    /**
     * Алгоритм пошуку страв за інгрідієнтами.
     */
    public void findMeals() {
//        boolean flag = false;
//        /**
//         * Пробігає циклом по кожній страві попередьно найдених страв
//         */
//        for (int i=0; i<Meals.findedList.size(); i++){
//            /**
//             * Пробігає циклом по кожному інгрідієнту
//             */
//            for(int j = 0; j< Meals.findedList.get(i).Ingridients.length; j++){
//                /**
//                 * Якщо знаходить інгрідієнт, то піднімає флажок
//                 */
//                if(ingridient.equals(Meals.meals[i].Ingridients[j])){
//                   flag = true;
//
//                }
//            }
//            /**
//             * Якщо флажок опущений, страву видаляємо
//             */
//            if(flag == false){
//                Meals.findedList.remove(Meals.meals[i]);
//            }
//
//        }

        Meals.findedList.clear();
        String str = "";
        int cout=0;
        for(int i = 0; i< Meals.meals.length; i++){
            for( int j = 0; j< Meals.meals[i].Ingredients.length; j++){
                str += Meals.meals[i].Ingredients[j];
            }
            for(int k = 0; k < Meals.choosedIngridients.size(); k++){
                if(str.contains(Meals.choosedIngridients.get(k))){
                    cout ++;
                }
            }
            if( cout == Meals.choosedIngridients.size()){
                Meals.findedList.add(Meals.meals[i]);
            }
            cout=0;
            str = "";

        }


    }
}
