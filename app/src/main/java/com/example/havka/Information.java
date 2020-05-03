package com.example.havka;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 *  Cторінка інформації для певної страви.
 *  Містить фото та детальну інформацію про страву.
 *  @version 0.0
 *
 */
public class Information extends AppCompatActivity {

    private TextView mealTitleInfo;
    private StringBuilder information = new StringBuilder();
    private ImageView mealImage;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argumentsFirstPage = getIntent().getExtras();
        String s = argumentsFirstPage.get("meal").toString();
        number = Integer.parseInt(s); // сторінка запускається з параметрами, для вибору страви
        setContentView(R.layout.activity_information);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Верхня панель
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.inforamation);

        // Переключатель сторінок
        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №5, №6, №7
         *  По стандарту вибрана сторінкка №5
         */
        final  Intent intentIngredients = new Intent(getApplicationContext(), Ingredients.class);
        final Intent INTENT_INSTRUCTIONS = new Intent(getApplicationContext(), Instructions.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ingridients:
                        intentIngredients.putExtra("meal", number);
                        startActivity(intentIngredients);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.inforamation:

                        return true;
                    case R.id.instructions:
                        INTENT_INSTRUCTIONS.putExtra("meal", number);
                        startActivity(INTENT_INSTRUCTIONS);
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

        readFile(number);
    }



    /*
     Зчитуємо інформацію з файлу і виводимо на екран
    */
    public void readFile(int i){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(Meals.meals[i].getFileName()))
            );

            String line;
            while ((line = reader.readLine()) != null) {
                information.append(line);
                information.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            TextView output = (TextView) findViewById(R.id.aboutMeals);
            output.setText((CharSequence) information);
            mealTitleInfo = (TextView) findViewById(R.id.mealTitleInfo);
            mealTitleInfo.setText(Meals.meals[i].getMealTitle());
            mealImage = (ImageView) findViewById(R.id.imageMeal);
            mealImage.setImageResource(Meals.meals[i].getMealImages());
        }
    }
    public StringBuilder getInformation(){
        return information;
    }
    public int[] Id = {
            R.id.aboutMeals,
            R.id.mealTitleInfo,
            R.id.imageMeal
    };

}
