package com.example.welovebasket.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.retrofit.RetrofitInstance
import com.example.welovebasket.roomDB.drinkDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homeViewModel(
    private val drinkDatabase : drinkDB
):ViewModel(
) {
    private var randomDrinkLiveData = MutableLiveData<Drink>()
    private var favDrinkLiveData = drinkDatabase.drinkDAO().getAllDrinks()
    fun getRandomDrink(){
        RetrofitInstance.api.getRandomDrink().enqueue(object : Callback<DrinksList>{
            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                if(response.body() != null){
                    val randomDrink: Drink = response.body()!!.drinks[0]
                    randomDrinkLiveData.value = (randomDrink)
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<DrinksList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun deleteMeal(drink:Drink){
        viewModelScope.launch {
            drinkDatabase.drinkDAO().deleteDrink(drink)
        }
    }

    fun getAllSavedMeals() {
        viewModelScope.launch(Dispatchers.Main) {
        }
    }

    fun insertMeal(drink:Drink){
        viewModelScope.launch(Dispatchers.IO) {
            drinkDatabase.drinkDAO().insertAndUpdate(drink)
            withContext(Dispatchers.Main){
            }
        }
    }

    fun observeRandomDrinkLiveData():LiveData<Drink> {
        return randomDrinkLiveData
    }

    fun observeFavDrinkLiveData():LiveData<List<Drink>>{
        return favDrinkLiveData
    }

}

