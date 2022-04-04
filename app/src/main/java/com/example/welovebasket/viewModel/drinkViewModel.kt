package com.example.welovebasket.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.welovebasket.activities.DrinkDetails
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.retrofit.RetrofitInstance
import com.example.welovebasket.roomDB.drinkDB
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkViewModel(
   val drinkDatabase : drinkDB
): ViewModel() {
    private var drinkDetailLiveData = MutableLiveData<Drink>()
    private val mutableDrinkBottomSheet = MutableLiveData<List<Drink>>()

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

    fun getMealByIdBottomSheet(id: String) {
        RetrofitInstance.api.getDrinkDetails(id).enqueue(object : Callback<DrinksList> {
            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                mutableDrinkBottomSheet.value = response.body()!!.drinks
            }

            override fun onFailure(call: Call<DrinksList>, t: Throwable) {
                Log.e("APPLICATION", t.message.toString())
            }

        })
    }

    fun observerDrinkLiveData():LiveData<Drink>{
        return drinkDetailLiveData
    }

    fun insertDrink(drink:Drink){
        viewModelScope.launch {
            drinkDatabase.drinkDAO().insertAndUpdate(drink)
        }
    }

    fun observeMealBottomSheet(): LiveData<List<Drink>> {
        return mutableDrinkBottomSheet
    }

    fun deleteDrink(drink:Drink){
        viewModelScope.launch {
            drinkDatabase.drinkDAO().deleteDrink(drink)
        }
    }

}