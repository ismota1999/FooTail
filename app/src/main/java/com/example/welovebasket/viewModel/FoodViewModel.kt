package com.example.welovebasket.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.classes.MealList
import com.example.welovebasket.retrofit.FoodRetrofitInstance
import com.example.welovebasket.retrofit.RetrofitInstance
import com.example.welovebasket.roomDB.foodDB
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodViewModel(
    private val foodDatabase : foodDB


    ): ViewModel() {
    private var foodLiveData = MutableLiveData<Meal>()

    fun getMealDetails(id:String){
        FoodRetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body()!=null){
                    foodLiveData.value = response.body()!!.meals[0]
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }
        })

    }

    fun observerMealLiveData():LiveData<Meal>{
        return foodLiveData
    }

    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            foodDatabase.foodDAO().insertMeal(meal)
        }
    }
}