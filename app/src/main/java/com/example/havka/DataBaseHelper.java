package com.example.havka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String MEALS_TABLE = "MEALS_TABLE";
    public static final String MEAL_TITLE = "MEAL_TITLE";
    public static final String MEAL_DESCRIPTION = "MEAL_DESCRIPTION";
    public static final String MEAL_PRICE = "MEAL_PRICE";
    public static final String MEAL_TIME = "MEAL_TIME";
    public static final String MEAL_CAPACITY = "MEAL_CAPACITY";
    public static final String MEAL_IMAGE = "MEAL_IMAGE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Havka.db", null, 1);
    }


    /**
     * Створюємо базу даних.
     * @param db - база даних SQLite
     */

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + MEALS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MEAL_TITLE + " TEXT, " + MEAL_DESCRIPTION + " TEXT, " + MEAL_PRICE + " TEXT, " + MEAL_TIME + " TEXT, " +
                MEAL_CAPACITY + " TEXT, " + MEAL_IMAGE + " INTEGER)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Цей метод додаэ страву в базу даних
     * @param mealModel - параметр, з якого видобуваємо
     * інформацію про страву
     * @return true якщо вставка пройшла успішно,
     * інакше false.
     */

    public boolean addOne(@NotNull MealModel mealModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MEAL_TITLE, mealModel.getMealTitle());
        cv.put(MEAL_DESCRIPTION, mealModel.getMealDescription());
        cv.put(MEAL_PRICE, mealModel.getMealPrice());
        cv.put(MEAL_TIME, mealModel.getMealTime());
        cv.put(MEAL_CAPACITY, mealModel.getMealCapacity());
        cv.put(MEAL_IMAGE, mealModel.getMealImages());

        long insert = db.insert(MEALS_TABLE, null, cv);

        return insert != -1;
    }

    /**
     * Даний метод збирає всю інформацію з бази даних
     * @return List<MealModel>
     */

    public List<MealModel> getAll(){
        List<MealModel> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + MEALS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int mealId = cursor.getInt(0);
                String titleId = cursor.getString(1);
                String descriptionId = cursor.getString(2);
                String priceId = cursor.getString(3);
                String timeId = cursor.getString(4);
                String capacityId = cursor.getString(5);
                int imageId = cursor.getInt(6);

                MealModel newMeal = new MealModel(titleId, descriptionId, priceId, timeId, capacityId, imageId);
                returnList.add(newMeal);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }

    public List<Integer> getAllID(){
        List<Integer> idArray = new ArrayList<>();

        String query = "SELECT * FROM " + MEALS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int mealId = cursor.getInt(0);
                idArray.add(mealId);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  idArray;
    }

    /**
     * Метод, який видаляє елемент з бази даних
     * @param name - назва страви, яку потрібно видалити
     */

    public void removeOne(String name){
       SQLiteDatabase db = this.getWritableDatabase();
       String deleteRow = "DELETE FROM " + MEALS_TABLE + " WHERE " + MEAL_TITLE + " = '" + name + "'";

       db.execSQL(deleteRow);
    }

    public void removeAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAll = "DELETE FROM " + MEALS_TABLE;

        db.execSQL(deleteAll);
    }
}
