package com.example.welovebasket.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.welovebasket.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animateLotties()
        handler()
    }

    fun animateLotties(){
        binding.lottie.animate().translationY(-1600F).setDuration(1000).setStartDelay(4000)
        binding.lottieCocktail.animate().translationY(-1600F).setDuration(1000).setStartDelay(4000)
    }

    fun handler(){
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}