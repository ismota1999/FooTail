package com.example.welovebasket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.welovebasket.roomDB.drinkDB
import com.example.welovebasket.roomDB.foodDB

class FoodVMFactory(
    private val foodDatabase : foodDB
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodViewModel(foodDatabase) as T
    }
}