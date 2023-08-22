package com.example.pokedex.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import com.example.pokedex.presenter.fragment.DialogFragment

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

    fun showToolbar(isVisible: Boolean) {
        binding.toolbar.myToolbar.isVisible = isVisible
    }

    private fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = DialogFragment()

        newFragment.show(fragmentManager, "dialog")
    }

}