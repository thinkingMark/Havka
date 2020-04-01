package com.example.havka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SortedMeals extends AppCompatActivity {

    ListView listView;
    String MealTitle[] = {"BORSHT","VARENYKY","UZVAR","SIRNIKS"};
    String MealDescription[] = {"Borsch is a sour soup common in Eastern Europe and Northern Asia.Made with beetroots as one of the main ingredients, which give the dish its distincti-ve red color. Nice dish.",
            "Ukrainian dish made of boiled dough with diverse fillings, such as meat,vegetables, fruits, cheese etc. These Ukrainian dumplings can be the main course as well as the dessert.",
            "The uzvar is a national Ukrainian beverage, cooked with dried fruits and berries.Some housewives tend to add species – star anise or nutmeg – that will give the drink an exotic flavor.",
            "Fried Eastern Slavic quark pancakes, garnished with sour cream, varenye, jam, honey or apple sauce. The cheese mixture may contain raisins for extra flavour. Nice dish"};
    String MealPrice[] = {"0.5$","0.8$","0.5$","3.5$"};
    String MealTime[] = {"1 H","2 h","20 m","40 m"};
    String MealCapacity[] = {"1 L","1 kg","1 l","1 kg"};
    int MealImages[] = {R.drawable.borsh,R.drawable.varenyky,R.drawable.uzvar,R.drawable.sirniks};
    List<MealModel> listMeals = new ArrayList<>();
    CustomAdapter customAdapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_meals);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);
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

        for(int i=0;i<MealTitle.length;i++){
            MealModel mealModel = new MealModel(MealTitle[i],MealDescription[i],MealPrice[i],MealTime[i],MealCapacity[i],MealImages[i]);
        }

        listView = (ListView)findViewById(R.id.listView);
        initList();
         editText = (EditText)findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                }
                else{
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    class CustomAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return MealImages.length;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.some_meal,null);

            RatingBar ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
            Button mButton = (Button)view.findViewById(R.id.meal_button);
            TextView mMealTitle = (TextView)view.findViewById(R.id.meal_title);
            TextView mMealDescription = (TextView)view.findViewById(R.id.meal_description);
            TextView mMealPrice = (TextView)view.findViewById(R.id.meal_price);
            TextView mMealTime = (TextView)view.findViewById(R.id.meal_time);
            TextView mMealCapacity = (TextView)view.findViewById(R.id.meal_capacity);
            ImageView mMealImage = (ImageView)view.findViewById(R.id.meal_image);


            mMealTitle.setText(MealTitle[position]);
            mMealDescription.setText(MealDescription[position]);
            mMealPrice.setText(MealPrice[position]);
            mMealTime.setText(MealTime[position]);
            mMealCapacity.setText(MealCapacity[position]);
            mMealImage.setImageResource(MealImages[position]);


            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    Toast.makeText(SortedMeals.this,"Value" + rating, Toast.LENGTH_LONG).show();
                }
            });

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

    public void searchItem(String textToSearch){
        for(int i=0;i<MealTitle.length;i++){
            if(!MealTitle[i].contains(textToSearch)){

            }
            customAdapter.notifyDataSetChanged();
        }

    }
    public void initList(){
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

}
