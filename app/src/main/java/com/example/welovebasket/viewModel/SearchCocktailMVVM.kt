package com.example.welovebasket.viewModel

import android.content.Context
import android.service.controls.ControlsProviderService
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.classes.MealList
import com.example.welovebasket.retrofit.FoodRetrofitInstance
import com.example.welovebasket.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCocktailMVVM : ViewModel() {

    private var searchedCocktailLiveData = MutableLiveData<Drink>()


    fun searchCocktail(ingredient : String, context: Context?){
        RetrofitInstance.api.getDrinkByName(ingredient).enqueue(object: Callback<DrinksList> {
            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                val searchedCocktail : List<Drink> = response.body()?.drinks?: listOf()
                if(searchedCocktail.isNotEmpty()){
                    searchedCocktailLiveData.value = searchedCocktail[0]
                }
                else{
                    Toast.makeText(context?.applicationContext, "Couldn't find a cocktail", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<DrinksList>, t: Throwable) {
                Log.e(ControlsProviderService.TAG, t.message.toString())
            }
        })
    }

    fun observeSearchLiveData(): LiveData<Drink> {
        return searchedCocktailLiveData
    }
}