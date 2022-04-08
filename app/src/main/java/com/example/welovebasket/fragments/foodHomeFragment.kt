package com.example.welovebasket.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.welovebasket.activities.FoodActivity
import com.example.welovebasket.activities.FoodDetails
import com.example.welovebasket.activities.MainActivity
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FragmentFoodHomeBinding
import com.example.welovebasket.viewModel.homeFoodViewModel
import com.example.welovebasket.viewModel.homeViewModel


class foodHomeFragment : Fragment() {
    private lateinit var binding:FragmentFoodHomeBinding
    private lateinit var foodHomeMVVM:homeFoodViewModel
    private lateinit var randomMeal: Meal

    companion object{
        const val MEAL_ID = "com.example.welovebasket.fragments.idMeal"
        const val MEAL_NAME = "com.example.welovebasket.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.welovebasket.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodHomeMVVM = (activity as FoodActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.generateDrink.setOnClickListener {
            foodHomeMVVM.getRandomMeal()
            observerRandomMeal()
            randomMealClicked()
        }

    }

    private fun randomMealClicked() {
        binding.randomCocktail.setOnClickListener {
            val intent = Intent(activity, FoodDetails::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        foodHomeMVVM.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { t ->
            Glide.with(this@foodHomeFragment)
                .load(t!!.strMealThumb)
                .into(binding.imgRandomCocktail)
            binding.drinkName.text = "You got : ${t!!.strMeal}"

            this.randomMeal = t
        }
    }
}


