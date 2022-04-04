package com.example.welovebasket.retrofit

import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:drinksApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v2/9973533/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(drinksApiInterface::class.java)
    }
}