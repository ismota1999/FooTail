package com.example.welovebasket.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.welovebasket.R
import com.example.welovebasket.databinding.ActivityDrinkDetailsBinding
import com.example.welovebasket.fragments.HomeFragment
import com.example.welovebasket.viewModel.DrinkViewModel

class DrinkDetails : AppCompatActivity() {
    private lateinit var drinkId:String
    private lateinit var drinkName:String
    private lateinit var drinkImg:String
    private lateinit var binding: ActivityDrinkDetailsBinding
    private lateinit var drinkMVVM: DrinkViewModel
    private lateinit var youtubeLink : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drinkMVVM = ViewModelProvider(this)[DrinkViewModel::class.java]

        getDrinkInfoFromIntent()

        setInformationInView()



        Loading()
        drinkMVVM.getDrinkDetail(drinkId)

        drinkDetailsLiveDataObserver()

        onVideoClick()
    }

    private fun onVideoClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            if(youtubeLink.isEmpty()) {
                binding.imgYoutube.visibility = View.INVISIBLE
            }
            else{
                startActivity(intent)
            }
        }
    }

    private fun drinkDetailsLiveDataObserver() {
        drinkMVVM.observerDrinkLiveData().observe(this
        ) { t ->
            onLoaded()
            val drink = t

            binding.category.text = "Category : ${drink!!.strCategory}"
            binding.glassType.text = "Glass : ${drink!!.strGlass}"
            binding.instructions.text = drink.strInstructions
            binding.drinkIngredients.text = drink.strIngredient1
            binding.drinkIngredients.text = drink.strIngredient2



            youtubeLink = drink?.strVideo

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
        binding.imgYoutube.visibility = View.INVISIBLE


    }

    private fun onLoaded(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.instructions.visibility = View.VISIBLE
        binding.category.visibility = View.VISIBLE
        binding.glassType.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE

    }
}