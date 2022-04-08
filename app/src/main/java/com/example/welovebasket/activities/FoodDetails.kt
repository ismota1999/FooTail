package com.example.welovebasket.activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.welovebasket.R
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.ActivityFoodDetailsBinding
import com.example.welovebasket.fragments.foodHomeFragment
import com.example.welovebasket.roomDB.foodDB
import com.example.welovebasket.viewModel.FoodVMFactory
import com.example.welovebasket.viewModel.FoodViewModel

class FoodDetails : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealImg:String
    private lateinit var binding: ActivityFoodDetailsBinding
    private lateinit var foodMVVM: FoodViewModel
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodDatabase = foodDB.getInstance(this)
        val viewModelFactory = FoodVMFactory(foodDatabase)

        foodMVVM = ViewModelProvider(this, viewModelFactory)[FoodViewModel::class.java]

        getMealInfoFromIntent()

        setInformationInView()

        Loading()
        foodMVVM.getMealDetails(mealId)

        mealDetailsLiveObserver()

        favoriteClick()

}

    private fun favoriteClick() {
        binding.btnSaveMeal.setOnClickListener {
            mealSaved?.let {
                foodMVVM.insertMeal(it)
                Toast.makeText(this, "Drink Saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var mealSaved : Meal? = null

    private fun mealDetailsLiveObserver() {
        foodMVVM.observerMealLiveData().observe(this)
        { t ->
            onLoaded()
            val meal = t
            mealSaved = meal

            binding.category.text = "Category : ${meal.strCategory?:"N/A"}"
            binding.mealArea.text = "Area : ${meal?.strArea?:"N/A"}"
            binding.instructions.text = meal.strInstructions

            val listItems = meal.getIngredients().map {
                "-" + it.ingredient + " -> " +  (it.measure?:"N/A")
            }
            listView = findViewById(R.id.listView)

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            listView.adapter = adapter
        }
    }



    private fun setInformationInView() {
        Glide.with(applicationContext)
            .load(mealImg)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(foodHomeFragment.MEAL_ID)?:"N/A"
        mealName = intent.getStringExtra(foodHomeFragment.MEAL_NAME)?:"N/A"
        mealImg = intent.getStringExtra(foodHomeFragment.MEAL_THUMB)?:"N/A"
    }

    private fun Loading(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSaveMeal.visibility = View.INVISIBLE
        binding.instructions.visibility = View.INVISIBLE
        binding.category.visibility = View.INVISIBLE
        binding.mealArea.visibility = View.INVISIBLE

    }

    private fun onLoaded() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSaveMeal.visibility = View.VISIBLE
        binding.instructions.visibility = View.VISIBLE
        binding.category.visibility = View.VISIBLE
        binding.mealArea.visibility = View.VISIBLE
    }


}

