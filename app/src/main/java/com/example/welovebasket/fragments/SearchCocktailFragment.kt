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
import com.bumptech.glide.Glide
import com.example.welovebasket.R
import com.example.welovebasket.activities.DrinkDetails
import com.example.welovebasket.adapters.CocktailSearcherAdapter
import com.example.welovebasket.adapters.MealSearchAdapter
import com.example.welovebasket.classes.Drink
import com.example.welovebasket.classes.Meal
import com.example.welovebasket.databinding.FragmentSearchCocktailBinding
import com.example.welovebasket.databinding.FragmentSearchMainIngredientBinding
import com.example.welovebasket.viewModel.SearchCocktailMVVM
import com.example.welovebasket.viewModel.SearchMVVM

class SearchCocktailFragment : Fragment() {

    private lateinit var myAdapter: CocktailSearcherAdapter
    private lateinit var binding: FragmentSearchCocktailBinding
    private lateinit var searchCocktailMVVM: SearchCocktailMVVM
    private var drinkId = ""
    private var drinkStr = ""
    private var drinkThumb = ""

    companion object {
        private var DRINK_ID = "com.example.welovebasket.fragments.idDrink"
        private var DRINK_NAME = "com.example.welovebasket.fragments.nameDrink"
        private var DRINK_THUMB = "com.example.welovebasket.fragments.thumbDrink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter = CocktailSearcherAdapter()
        searchCocktailMVVM = ViewModelProvider(this)[SearchCocktailMVVM::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCocktailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSearchClick()
        observeSearch()
        onSearchDrinkClicked()
    }

    private fun onSearchDrinkClicked() {
        binding.searchedCocktailCard.setOnClickListener {
            val intent = Intent(context, DrinkDetails::class.java)
            intent.putExtra(DRINK_ID, drinkId)
            intent.putExtra(DRINK_NAME, drinkStr)
            intent.putExtra(DRINK_THUMB, drinkThumb)
            startActivity(intent)
        }
    }

    private fun onSearchClick() {
        binding.icSearch.setOnClickListener {
            searchCocktailMVVM.searchCocktail(binding.edSearch.text.toString(), context)
        }
    }

    private fun observeSearch() {
        searchCocktailMVVM.observeSearchLiveData()
            .observe(viewLifecycleOwner, object : Observer<Drink> {
                override fun onChanged(t: Drink?) {
                    if (t == null) {
                        Toast.makeText(context, "No such drink", Toast.LENGTH_SHORT).show()
                    } else {
                        binding.apply {
                            drinkId = t.idDrink
                            drinkStr = t.strDrink!!
                            drinkThumb = t.strDrinkThumb!!
                            Glide.with(context!!.applicationContext)
                                .load(t.strDrinkThumb)
                                .into(imgSearchedCocktail)
                            tvSearchedCocktail.text = t.strDrink
                            searchedCocktailCard.visibility = View.VISIBLE
                        }
                    }
                }
            })
    }
}