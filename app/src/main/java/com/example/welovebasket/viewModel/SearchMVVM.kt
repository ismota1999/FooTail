package com.example.welovebasket.viewModel

import android.content.Context
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.classes.MealList
import com.example.welovebasket.fragments.searchMainIngredient
import com.example.welovebasket.retrofit.FoodRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMVVM : ViewModel(){
    private var searchedIngredientLiveData = MutableLiveData<Meal>()


    fun searchMealIngredient(ingredient : String, context: Context?){
        FoodRetrofitInstance.api.getMealByName(ingredient).enqueue(object: Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                val searchedMeal : List<Meal> = response.body()?.meals?: listOf()
                if(searchedMeal.isNotEmpty()){
                           searchedIngredientLiveData.value = searchedMeal[0]
                       }
                else{
                    Toast.makeText(context?.applicationContext, "Couldn't find a meal", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun observeSearchLiveData():LiveData<Meal> {
        return searchedIngredientLiveData
    }
}