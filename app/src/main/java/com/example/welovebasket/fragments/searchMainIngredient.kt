package com.example.welovebasket.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.welovebasket.activities.DrinkDetails
import com.example.welovebasket.activities.FoodActivity
import com.example.welovebasket.activities.FoodDetails
import com.example.welovebasket.adapters.FavDrinksAdapter
import com.example.welovebasket.adapters.MealSearchAdapter
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FragmentSearchMainIngredientBinding
import com.example.welovebasket.viewModel.SearchMVVM
import com.example.welovebasket.viewModel.homeFoodViewModel

class searchMainIngredient : Fragment() {

    private lateinit var myAdapter: MealSearchAdapter
    private lateinit var binding: FragmentSearchMainIngredientBinding
    private lateinit var searchMVVM: SearchMVVM
    private var mealId = ""
    private var mealStr = ""
    private var mealThumb = ""

    companion object {
        private var MEAL_ID = "com.example.welovebasket.fragments.idMeal"
        private var MEAL_NAME = "com.example.welovebasket.fragments.nameMeal"
        private var MEAL_THUMB = "com.example.welovebasket.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter = MealSearchAdapter()
        searchMVVM = ViewModelProvider(this)[SearchMVVM::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMainIngredientBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSearchClick()
        observeSearch()
        onSearchFoodClick()

    }

    private fun onSearchFoodClick() {
        binding.imgSearchedMeal.setOnClickListener {
            val intent = Intent(context, FoodDetails::class.java)
            intent.putExtra(MEAL_ID, mealId)
            intent.putExtra(MEAL_NAME, mealStr)
            intent.putExtra(MEAL_THUMB, mealThumb)
            startActivity(intent)
        }
    }


    private fun onSearchClick() {
        binding.icSearch.setOnClickListener {
            searchMVVM.searchMealIngredient(binding.edSearch.text.toString(), context)

        }
    }

    private fun observeSearch() {
        searchMVVM.observeSearchLiveData()
            .observe(viewLifecycleOwner, object : Observer<Meal> {
                override fun onChanged(t: Meal?) {
                    if (t == null) {
                        Toast.makeText(context, "No such a meal", Toast.LENGTH_SHORT).show()
                    } else {
                        binding.apply {
                            mealId = t.idMeal
                            mealStr = t.strMeal!!
                            mealThumb = t.strMealThumb!!
                            Glide.with(context!!.applicationContext)
                                .load(t.strMealThumb)
                                .into(imgSearchedMeal)
                            tvSearchedMeal.text = t.strMeal
                            searchedMealCard.visibility = View.VISIBLE
                        }
                    }
                }
            })
    }
}
