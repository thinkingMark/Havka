package com.example.havka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Ingredients extends AppCompatActivity {

    private EditText capacity;
    private Button generate;
    private TableLayout ingredientsLayout;
    private CheckBox checkBox;
    private TextView textView;
    private Meals meals;
    private int number;
    private TextView title;
    private TextView capacityMeal;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argumentsFirstPage = getIntent().getExtras();
        String s = argumentsFirstPage.get("meal").toString();
        number = Integer.parseInt(s); // сторінка запускається з параметрами, для вибору страви
        setContentView(R.layout.activity_ingridients);

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.ingridients);

        // Верхня панель
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        final Intent intentInformation = new Intent(getApplicationContext(),Information.class);
        final Intent INTENT_INSTRUCTIONS = new Intent(getApplicationContext(), Instructions.class);

        // Переключатель сторінок
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ingridients:

                        return true;
                    case R.id.inforamation:
                        intentInformation.putExtra("meal", number);
                        startActivity(intentInformation);
                        overridePendingTransition(0,0);
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
        addListenerButton(number);
    }


    @SuppressLint({"SetTextI18n", "CutPasteId"})
    public void addListenerButton(final int number) {
        capacity = (EditText)findViewById(R.id.capacityMeal);
        generate = (Button)findViewById(R.id.generateLiters);
        ingredientsLayout = (TableLayout)findViewById(R.id.table_layout);
        meals = new Meals();
        checkBox = new CheckBox(this);
        title = (TextView)findViewById(R.id.mealTitleInfo);
        capacityMeal = (TextView)findViewById(R.id.capacityMeal);

        switch (number){
            case 0:
                title.setText("BORSCHT");
                capacityMeal.setHint("LITERS");
                break;
            case 1:
                title.setText("VARENYKY");
                capacityMeal.setHint("KILOGRAMS");
                break;
            case 2:
                title.setText("UZVAR");
                capacityMeal.setHint("LITERS");
                break;
            case 3:
                title.setText("SIRNIKS");
                capacityMeal.setHint("KILOGRAMS");
                break;
            default:
                break;
        }


        generate.setOnClickListener(
                new View.OnClickListener() {

                    /*
                    в наступному методі відбувається генерація продуктів, в якій обраховуються пропорції інгредієнтів
                     */

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        try {
                            double valueOfIngredients = Double.parseDouble(capacity.getText().toString());
                            double proportion = 20 * (valueOfIngredients - 1);

                            if(!capacity.getText().toString().equals("")) {
                                Toast.makeText(
                                        Ingredients.this, capacity.getText(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            if (flag) deleteLayouts();
                            chooseMeal(proportion);
                        } catch (Exception exc){
                            Toast.makeText(Ingredients.this,"Please input a count of meal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    /**
     * Відповідео до number обираємо
     * інгредієнти страви
     * @param value
     */

    @SuppressLint("SetTextI18n")
    private void chooseMeal(double value){
        switch (number){
            case 0:
                displayIngredients(
                        Meals.firstMealIngridients,
                        Meals.defaultIngredientsBorsch,
                        value
                );
                break;
            case 1:
                displayIngredients(
                        Meals.secondMealIngridients,
                        Meals.defaultIngredientsVarenyky,
                        value
                );
                break;
            case 2:
                displayIngredients(
                        Meals.thirdMealIngridients,
                        Meals.defaultIngredientsUzvar,
                        value
                );
                break;
            case 3:
                displayIngredients(
                        Meals.fourthMealIngridients,
                        Meals.defaultIngredientsSirniks,
                        value
                );
                break;
            default:
                break;
        }
    }

    /**
     * Запускаємо і виводимо інгредієнти
     * @param mealsIngredients - інгредієнти страви
     * @param defaultProportion - пропорції інгредієнтів
     * @param value - к-сть страви(літри або кілограми)
     */

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "RtlHardcoded"})
    public void displayIngredients(String[] mealsIngredients, int[] defaultProportion, double value){
        flag = true;
        for(int i = 0; i < mealsIngredients.length; i++){
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            TableRow row = new TableRow(this);
            checkBox = new CheckBox(this);
            textView = new TextView(this);

            row.setLayoutParams(layoutParams);


            checkBox.setText("\t\t\t" + mealsIngredients[i]);
            checkBox.setTextSize(24);
            checkBox.setTypeface(ResourcesCompat.getFont(this, R.font.comfortaa_light));
            checkBox.setTextColor(R.color.colorText);

            textView.setText("\t\t\t\t\t\t\t\t"  + (defaultProportion[i] + value) + " g");
            textView.setTextSize(24);
            textView.setGravity(Gravity.RIGHT);
            textView.setTypeface(ResourcesCompat.getFont(this, R.font.comfortaa_light));
            textView.setTextColor(R.color.colorText);


            row.addView(checkBox);
            row.addView(textView);
            ingredientsLayout.addView(row, i);
        }
    }

    /**
     * Створений для того, щоб очищувати список
     * кожного разу, коли ми нажимаємо кнопку "Generate"
     */

    public void  deleteLayouts() {
        ingredientsLayout.removeAllViewsInLayout();
    }
}
