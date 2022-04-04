package com.example.welovebasket.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.classes.MealList
import com.example.welovebasket.retrofit.FoodRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homeFoodViewModel:ViewModel(){

    private var randomMealLiveData = MutableLiveData<Meal>()

    fun getRandomMeal(){
        FoodRetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body()!=null){
                   val randomMeal : Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = (randomMeal)
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("FoodHomeFragment", t.message.toString())
            }
        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }
}