package com.example.welovebasket.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodRetrofitInstance {

    val api:foodApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(foodApiInterface::class.java)
    }
}