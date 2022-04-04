package com.example.welovebasket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.welovebasket.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = Navigation.findNavController(this, R.id.frag_food_host)
        NavigationUI.setupWithNavController(bottomNavigation,navController)

    }
}