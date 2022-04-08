package com.example.welovebasket.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.welovebasket.R
import com.example.welovebasket.activities.DrinkDetails
import com.example.welovebasket.activities.FoodActivity
import com.example.welovebasket.activities.FoodDetails
import com.example.welovebasket.activities.MainActivity
import com.example.welovebasket.adapters.FavDrinksAdapter
import com.example.welovebasket.adapters.FavFoodsAdapter
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FragmentFavoriteFoodBinding
import com.example.welovebasket.databinding.FragmentFavoritesBinding
import com.example.welovebasket.viewModel.homeFoodViewModel
import com.example.welovebasket.viewModel.homeViewModel

class FavoriteFoodFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteFoodBinding
    private lateinit var viewModel : homeFoodViewModel
    private lateinit var favFoodsAdapter: FavFoodsAdapter

    companion object{
        const val MEAL_ID = "com.example.welovebasket.fragments.idMeal"
        const val MEAL_NAME = "com.example.welovebasket.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.welovebasket.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteFoodBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observerFavs()
        favMealClicked()

    }

    private fun favMealClicked(){
        favFoodsAdapter.setOnFavoriteMealListener(object : FavFoodsAdapter.OnFavoriteClickListener{
            override fun onFavoriteClick(meal: Meal) {
                val intent = Intent(activity, FoodDetails::class.java)
                intent.putExtra(MEAL_ID,meal.idMeal)
                intent.putExtra(MEAL_NAME,meal.strMeal)
                intent.putExtra(MEAL_THUMB,meal.strMealThumb)
                startActivity(intent)
            }

        })
    }

    private fun prepareRecyclerView() {
        favFoodsAdapter = FavFoodsAdapter()
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favFoodsAdapter
        }
    }

    private fun observerFavs() {
        viewModel.observeFavMealLiveData().observe(viewLifecycleOwner, Observer { meals->
            favFoodsAdapter.differ.submitList(meals)
        })
    }
}