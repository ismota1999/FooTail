package com.example.welovebasket.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.welovebasket.activities.DrinkDetails
import com.example.welovebasket.activities.MainActivity
import com.example.welovebasket.adapters.FavDrinksAdapter
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.databinding.FragmentFavoritesBinding
import com.example.welovebasket.viewModel.homeViewModel
import com.google.android.material.snackbar.Snackbar


class FavoritesFragment : Fragment() {

    private lateinit var binding:FragmentFavoritesBinding
    private lateinit var viewModel : homeViewModel
    private lateinit var favDrinksAdapter:  FavDrinksAdapter

    companion object{
        const val DRINK_ID = "com.example.welovebasket.fragments.idDrink"
        const val DRINK_NAME = "com.example.welovebasket.fragments.nameDrink"
        const val DRINK_THUMB = "com.example.welovebasket.fragments.thumbDrink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observerFavs()
        favDrinkClicked()

    }

    private fun favDrinkClicked(){
        favDrinksAdapter.setOnFavoriteDrinkListener(object : FavDrinksAdapter.OnFavoriteClickListener{
            override fun onFavoriteClick(drink: Drink) {
                val intent = Intent(activity, DrinkDetails::class.java)
                intent.putExtra(DRINK_ID,drink.idDrink)
                intent.putExtra(DRINK_NAME,drink.strDrink)
                intent.putExtra(DRINK_THUMB,drink.strDrinkThumb)
                startActivity(intent)
            }

        })
    }

    private fun prepareRecyclerView() {
        favDrinksAdapter = FavDrinksAdapter()
            binding.rvFavorites.apply {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                adapter = favDrinksAdapter
            }
        }

    private fun observerFavs() {
        viewModel.observeFavDrinkLiveData().observe(viewLifecycleOwner, Observer { drinks->
            favDrinksAdapter.differ.submitList(drinks)
        })
    }
}