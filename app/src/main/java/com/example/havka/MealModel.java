package com.example.havka;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *  Модель страви.
 */
public class MealModel extends ArrayList<Parcelable> {

    String MealTitle;
    String MealDescription;
    String MealPrice;
    String MealTime;
    String MealCapacity;
    String[] Ingredients;
    int MealImages;
    String FileName;
    boolean isFavourite;
    byte[] MealImage;

    public MealModel(String mealTitle, String mealDescription,String mealPrice, String mealTime,
                     String mealCapacity, int mealImages) {
        MealTitle = mealTitle;
        MealDescription = mealDescription;
        MealPrice = mealPrice;
        MealTime = mealTime;
        MealCapacity = mealCapacity;
        MealImages = mealImages;
    }
    public MealModel(String mealTitle, String mealDescription,String mealPrice, String mealTime,
                     String mealCapacity, int mealImages, String[] ingridients, String fileName) {
        MealTitle = mealTitle;
        MealDescription = mealDescription;
        MealPrice = mealPrice;
        MealTime = mealTime;
        MealCapacity = mealCapacity;
        MealImages = mealImages;
        Ingredients = ingridients;
        FileName = fileName;
    }

    public MealModel(String mealTitle, String mealDescription,String mealPrice, String mealTime,
                     String mealCapacity, byte[] arr) {
        MealTitle = mealTitle;
        MealDescription = mealDescription;
        MealPrice = mealPrice;
        MealTime = mealTime;
        MealCapacity = mealCapacity;
        MealImage = arr;
    }

    public String getMealTitle() {
        return MealTitle;
    }

    public void setMealTitle(String mealTitle) {
        MealTitle = mealTitle;
    }

    public String getMealDescription() {
        return MealDescription;
    }

    public void setMealDescription(String mealDescription) {
        MealDescription = mealDescription;
    }

    public String getMealPrice() {
        return MealPrice;
    }

    public void setMealPrice(String mealPrice) {
        MealPrice = mealPrice;
    }

    public String getMealTime() {
        return MealTime;
    }

    public void setMealTime(String mealTime) {
        MealTime = mealTime;
    }

    public String getMealCapacity() {
        return MealCapacity;
    }

    public void setMealCapacity(String mealCapacity) {
        MealCapacity = mealCapacity;
    }

    public int getMealImages() {
        return MealImages;
    }

    public String getFileName() {
        return FileName;
    }

    public void setMealImages(int mealImages) {
        MealImages = mealImages;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }



    public String[] getIngridients() {
        return Ingredients;
    }

    public void setIngridients(String[] ingridients) {
        Ingredients = ingridients;
    }


    @NonNull
    @Override
    public Stream<Parcelable> stream() {
        return null;
    }

    @NonNull
    @Override
    public Stream<Parcelable> parallelStream() {
        return null;
    }
}
