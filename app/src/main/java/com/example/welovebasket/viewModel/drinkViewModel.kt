package com.example.welovebasket.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkViewModel(): ViewModel() {
    private var drinkDetailLiveData = MutableLiveData<Drink>()

    fun getDrinkDetail(id:String){
        RetrofitInstance.api.getDrinkDetails(id).enqueue(object : Callback<DrinksList>{
            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                if(response.body()!=null){
                    drinkDetailLiveData.value = response.body()!!.drinks[0]
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<DrinksList>, t: Throwable) {
                Log.d("DrinkActivity", t.message.toString())
            }
        })

    }
    fun observerDrinkLiveData():LiveData<Drink>{
        return drinkDetailLiveData
    }

}