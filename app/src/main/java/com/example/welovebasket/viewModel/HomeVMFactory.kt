package com.example.welovebasket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.welovebasket.roomDB.drinkDB

class HomeVMFactory(
    private val drinkDatabase : drinkDB
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return homeViewModel(drinkDatabase) as T
    }
}