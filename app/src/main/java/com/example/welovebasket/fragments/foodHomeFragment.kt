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
        const val DRINK_ID = "com.example.welovebasket.fragments.idDrink"
        const val DRINK_NAME = "com.example.welovebasket.fragments.nameDrink"
        const val DRINK_THUMB = "com.example.welovebasket.fragments.thumbDrink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodHomeMVVM = ViewModelProvider(this)[homeFoodViewModel::class.java]
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
            //randomDrinkClicked()
        }

    }

    /*private fun randomDrinkClicked() {
        binding.randomCocktail.setOnClickListener {
            val intent = Intent(activity, DrinkDetails::class.java)
            intent.putExtra(DRINK_ID, randomDrink.idDrink)
            intent.putExtra(DRINK_NAME, randomDrink.strDrink)
            intent.putExtra(DRINK_THUMB, randomDrink.strDrinkThumb)
            startActivity(intent)
        }
    }*/

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


