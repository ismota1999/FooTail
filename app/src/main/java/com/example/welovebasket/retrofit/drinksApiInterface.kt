package com.example.welovebasket.retrofit

import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.classes.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface drinksApiInterface {


    @GET("random.php")
    fun getRandomDrink():Call<DrinksList>

    @GET("lookup.php?")
    fun getDrinkDetails(@Query("i")id:String):Call<DrinksList>

    @GET("search.php?")
    fun getDrinkByName(@Query("s") s:String):Call<DrinksList>



}