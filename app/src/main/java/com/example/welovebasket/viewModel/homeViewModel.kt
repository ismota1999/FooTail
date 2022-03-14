package com.example.welovebasket.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class homeViewModel():ViewModel() {
    private var randomDrinkLiveData = MutableLiveData<Drink>()
    fun getRandomDrink(){
        RetrofitInstance.api.getRandomDrink().enqueue(object : retrofit2.Callback<DrinksList>{
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

    fun observeRandomDrinkLiveData():LiveData<Drink> {
        return randomDrinkLiveData
    }
}

