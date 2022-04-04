package com.example.welovebasket.retrofit

import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.classes.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface foodApiInterface {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i")id:String): Call<MealList>



}