package com.example.welovebasket.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.welovebasket.R
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.databinding.ActivityDrinkDetailsBinding
import com.example.welovebasket.fragments.HomeFragment
import com.example.welovebasket.roomDB.drinkDB
import com.example.welovebasket.viewModel.DrinkVMFactory
import com.example.welovebasket.viewModel.DrinkViewModel

class DrinkDetails : AppCompatActivity() {
    private lateinit var drinkId:String
    private lateinit var drinkName:String
    private lateinit var drinkImg:String
    private lateinit var binding: ActivityDrinkDetailsBinding
    private lateinit var drinkMVVM: DrinkViewModel
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drinkDatabase = drinkDB.getInstance(this)
        val viewModelFactory = DrinkVMFactory(drinkDatabase)

        drinkMVVM = ViewModelProvider(this, viewModelFactory)[DrinkViewModel::class.java]

        getDrinkInfoFromIntent()

        setInformationInView()



        Loading()
        drinkMVVM.getDrinkDetail(drinkId)

        drinkDetailsLiveDataObserver()
        favoriteClick()
    }

    private fun favoriteClick() {
        binding.btnSave.setOnClickListener {
            drinkSaved?.let {
                drinkMVVM.insertDrink(it)
                Toast.makeText(this, "Drink Saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var drinkSaved : Drink? = null
    private fun drinkDetailsLiveDataObserver() {
        drinkMVVM.observerDrinkLiveData().observe(this
        ) { t ->
            onLoaded()
            val drink = t
            drinkSaved = drink

            binding.category.text = "Category : ${drink!!.strCategory}"
            binding.glassType.text = "Glass : ${drink!!.strGlass}"
            binding.instructions.text = drink.strInstructions

            val ingredient = drink.getIngredients()

            listView = findViewById<ListView>(R.id.listView)
            val listItems = arrayOfNulls<String>(ingredient.size)

            for (i in 0 until ingredient.size) {
                val recipe = ingredient[i]
                listItems[i] = "-" + recipe.ingredient + " -> " +  recipe.measure
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            listView.adapter = adapter

        }
    }

    private fun setInformationInView() {
        Glide.with(applicationContext)
            .load(drinkImg)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = drinkName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getDrinkInfoFromIntent() {
        val intent = intent
        drinkId = intent.getStringExtra(HomeFragment.DRINK_ID)!!
        drinkName = intent.getStringExtra(HomeFragment.DRINK_NAME)!!
        drinkImg = intent.getStringExtra(HomeFragment.DRINK_THUMB)!!

    }

    private fun Loading(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.INVISIBLE
        binding.instructions.visibility = View.INVISIBLE
        binding.category.visibility = View.INVISIBLE
        binding.glassType.visibility = View.INVISIBLE



    }

    private fun onLoaded(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.instructions.visibility = View.VISIBLE
        binding.category.visibility = View.VISIBLE
        binding.glassType.visibility = View.VISIBLE


    }
}