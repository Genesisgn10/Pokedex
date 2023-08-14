package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //splash screen transition.
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar.myToolbar)
        setContentView(binding.root)

        binding.toolbar.floatingActionButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = Dialog()

        newFragment.show(fragmentManager, "dialog")
    }

}