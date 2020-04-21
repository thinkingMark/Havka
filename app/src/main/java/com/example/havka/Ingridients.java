package com.example.havka;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
/**
 *
 *  Cторінка інгрієнтів для приготування страви. За архітектурою сторінка №7
 *  Містить калькулятор, що підлаштовується під необхідку ємність приготування.
 *  Виводить в кількості необхідий інгрідієнт.
 *  @version 0.0
 */
public class Ingridients extends AppCompatActivity {

    private EditText liter;
    private Button generate;

    private int[] arrayOfProportion = { // інгдедієнти
            100, 150, // sausage, beet
            70, 200,  // carrot, potato,
            50, 70,   // oil, onion
            100, 700, // paste, water,
            100, 100, // cabbage, tomato,
            10, 15,   // garlic, pepper,
            15, 30    // salt, parsley
    };

    /*
    Створюємо змінні для списку продуктів
     */

    private TextView sausage;
    private TextView beet;
    private TextView carrot;
    private TextView potato;
    private TextView oil;
    private TextView onion;
    private TextView paste;
    private TextView water;
    private TextView cabbage;
    private TextView tomato;
    private TextView garlic;
    private TextView pepper;
    private TextView salt;
    private TextView parsley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingridients);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.ingridients);

        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №5, №6, №7
         *  По стандарту вибрана сторінкка №7
         */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ingridients:

                        return true;
                    case R.id.inforamation:
                        startActivity(new Intent(getApplicationContext(),Information.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.instructions:
                        startActivity(new Intent(getApplicationContext(),Instructions.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        addListenerButton();
    }

    public void addListenerButton() {
        liter = (EditText)findViewById(R.id.liter);
        generate = (Button)findViewById(R.id.generateLiters);

        /*
        Додаємо продукти на панель, присвоюючи їм id
         */

        sausage = (TextView)findViewById(R.id.valueOfSousage);
        beet = (TextView)findViewById(R.id.valueOfBeet);
        carrot = (TextView)findViewById(R.id.valueOfCarrot);
        potato = (TextView)findViewById(R.id.valueOfPotato);
        oil = (TextView)findViewById(R.id.valueOfOil);
        onion = (TextView)findViewById(R.id.valueOfOnion);
        paste = (TextView)findViewById(R.id.valueOfPast);
        water = (TextView)findViewById(R.id.valueOfWater);
        cabbage = (TextView)findViewById(R.id.valueOfCabbage);
        tomato = (TextView)findViewById(R.id.valueOfTomato);
        garlic = (TextView)findViewById(R.id.valueOfGarlic);
        pepper = (TextView)findViewById(R.id.valueOfPepper);
        salt = (TextView)findViewById(R.id.valueOfSalt);
        parsley = (TextView)findViewById(R.id.valueOfParsley);

        generate.setOnClickListener(
                new View.OnClickListener() {

                    /*
                    в наступному методі відбувається генерація продуктів, в якій обраховуються пропорції інгредієнтів
                     */

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(
                                Ingridients.this, liter.getText(),
                                Toast.LENGTH_SHORT
                        ).show();

                        int valueOfIngredients = Integer.parseInt(liter.getText().toString());
                        int proportion = 20*(valueOfIngredients - 1);

                        sausage.setText(new Integer(arrayOfProportion[0] + proportion).toString());
                        beet.setText(new Integer(arrayOfProportion[1] + proportion).toString());
                        carrot.setText(new Integer(arrayOfProportion[2] + proportion).toString());
                        potato.setText(new Integer(arrayOfProportion[3] + proportion).toString());
                        oil.setText(new Integer(arrayOfProportion[4] + proportion).toString());
                        onion.setText(new Integer(arrayOfProportion[5] + proportion).toString());
                        paste.setText(new Integer(arrayOfProportion[6] + proportion).toString());
                        water.setText(new Integer(arrayOfProportion[7] + 900 * (valueOfIngredients - 1)).toString());
                        cabbage.setText(new Integer(arrayOfProportion[8] + proportion).toString());
                        tomato.setText(new Integer(arrayOfProportion[9] + proportion).toString());
                        garlic.setText(new Integer(arrayOfProportion[10] + proportion / 2).toString());
                        pepper.setText(new Integer(arrayOfProportion[11] + proportion / 4).toString());
                        salt.setText(new Integer(arrayOfProportion[12] + proportion / 4).toString());
                        parsley.setText(new Integer(arrayOfProportion[13] + proportion / 2).toString());
                    }
                }
        );
    }
}
