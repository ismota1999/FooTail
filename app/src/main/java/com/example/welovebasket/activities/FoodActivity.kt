package com.example.welovebasket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.welovebasket.R
import com.example.welovebasket.roomDB.drinkDB
import com.example.welovebasket.roomDB.foodDB
import com.example.welovebasket.viewModel.HomeFoodVMFactory
import com.example.welovebasket.viewModel.HomeVMFactory
import com.example.welovebasket.viewModel.homeFoodViewModel
import com.example.welovebasket.viewModel.homeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodActivity : AppCompatActivity() {
    val viewModel: homeFoodViewModel by lazy {
        val foodDatabase = foodDB.getInstance(this)
        val homeFoodVMFactory = HomeFoodVMFactory(foodDatabase)
        ViewModelProvider(this, homeFoodVMFactory)[homeFoodViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = Navigation.findNavController(this, R.id.frag_food_host)
        NavigationUI.setupWithNavController(bottomNavigation,navController)

    }
}