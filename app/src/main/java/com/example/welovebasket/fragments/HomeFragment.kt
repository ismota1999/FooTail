package com.example.welovebasket.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.welovebasket.R
import com.example.welovebasket.activities.DrinkDetails
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.DrinksList
import com.example.welovebasket.databinding.FragmentHomeBinding
import com.example.welovebasket.retrofit.RetrofitInstance
import com.example.welovebasket.viewModel.homeViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeMVVM:homeViewModel
    private lateinit var randomDrink: Drink

    companion object{
        const val DRINK_ID = "com.example.welovebasket.fragments.idDrink"
        const val DRINK_NAME = "com.example.welovebasket.fragments.nameDrink"
        const val DRINK_THUMB = "com.example.welovebasket.fragments.thumbDrink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMVVM = ViewModelProvider(this)[homeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMVVM.getRandomDrink()
        observerRandomDrink()
        randomDrinkClicked()



    }

    private fun randomDrinkClicked() {
        binding.randomCocktail.setOnClickListener {
            val intent = Intent(activity, DrinkDetails::class.java)
            intent.putExtra(DRINK_ID, randomDrink.idDrink)
            intent.putExtra(DRINK_NAME, randomDrink.strDrink)
            intent.putExtra(DRINK_THUMB, randomDrink.strDrinkThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomDrink() {
        homeMVVM.observeRandomDrinkLiveData().observe(viewLifecycleOwner
        ) { t ->
            Glide.with(this@HomeFragment)
                .load(t!!.strDrinkThumb)
                .into(binding.imgRandomCocktail)

            this.randomDrink = t
        }
    }

}