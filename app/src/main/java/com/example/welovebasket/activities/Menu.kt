package com.example.welovebasket.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.welovebasket.R
import com.example.welovebasket.databinding.ActivityMenuBinding
import com.example.welovebasket.fragments.HomeFragment

class Menu : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animateIcons()
        goToCocktails()
        goToFood()


    }

    private fun goToFood() {

        binding.burguerIcon.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun goToCocktails() {
        binding.cocktailIcon.setOnClickListener {
            /*val myFragment = HomeFragment()
            val fragment : Fragment? = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

            if (fragment !is HomeFragment){
                supportFragmentManager.beginTransaction()
                    .add(myFragment, HomeFragment::class.java.simpleName)
                    .commit()*/
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            }
        }


    private fun animateIcons() {
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)

        binding.burguerIcon.animation = rotate
        binding.cocktailIcon.animation = rotate
    }


}
