package com.example.havka;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;
/**
 *
 *  Cторінка інструкції приготування страви. За архітектурою сторінка №6
 *  Містить порядок приготування та checkbox'и для зручності
 *  Містить таймер приготування.
 *  @version 0.0
 */
public class Instructions extends AppCompatActivity {
    public static final long START_TIME_IN_MILLIS = 900000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private int number;
    private TextView mealTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argumentsFirstPage = getIntent().getExtras();
        String s = argumentsFirstPage.get("meal").toString();
        number = Integer.parseInt(s); // сторінка запускається з параметрами, для вибору страви
        setContentView(R.layout.activity_instructions);

        mTextViewCountDown = findViewById(R.id.text_View_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_Reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 resetTimer();
            }
        });

        updateCountDownText();

        // Ініцілізуємо змінну та присвоюємо їй обєкт з activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootom_navigation);

        // Верхня панель
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);

        // Спочатку вибране "Meals",бо це головна сторінка
        bottomNavigationView.setSelectedItemId(R.id.instructions);

        /**
         *  Нижнє поле навігації.
         *  Перехід між сторінками №5, №6, №7
         *  По стандарту вибрана сторінкка №6
         */

        final Intent INTENT_INFORMATION = new Intent(getApplicationContext(),Information.class);
        final Intent INTENT_INGREDIENTS = new Intent(getApplicationContext(), Ingredients.class);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ingridients:
                        INTENT_INGREDIENTS.putExtra("meal", number);
                        startActivity(INTENT_INGREDIENTS);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.inforamation:
                        INTENT_INFORMATION.putExtra("meal", number);
                        startActivity(INTENT_INFORMATION);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.instructions:

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

        selectMeal(number);
    }

    @SuppressLint("SetTextI18n")
    private void selectMeal(int number){
        mealTitle = (TextView)findViewById(R.id.mealInstruction);
        switch (number){
            case 0:
                mealTitle.setText("BORSCHT");
                break;
            case 1:
                mealTitle.setText("VARENYKY");
                break;
            case 2:
                mealTitle.setText("UZVAR");
                break;
            case 3:
                mealTitle.setText("SIRNIKS");
                break;
            default:
                break;
        }
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning= false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timeLeftFormatted);

    }
}
