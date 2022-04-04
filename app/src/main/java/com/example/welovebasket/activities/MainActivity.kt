package com.example.welovebasket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.welovebasket.R
import com.example.welovebasket.roomDB.drinkDB
import com.example.welovebasket.viewModel.HomeVMFactory
import com.example.welovebasket.viewModel.homeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel: homeViewModel by lazy {
        val drinkDatabase = drinkDB.getInstance(this)
        val homeVMFactory = HomeVMFactory(drinkDatabase)
        ViewModelProvider(this, homeVMFactory)[homeViewModel::class.java]
    }
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = Navigation.findNavController(this, R.id.frag_host)
        NavigationUI.setupWithNavController(bottomNavigation,navController)

    }
}