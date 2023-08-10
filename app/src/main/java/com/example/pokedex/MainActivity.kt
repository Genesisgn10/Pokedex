package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import android.widget.Toast
import com.example.pokedex.databinding.ActivityMainBinding
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.load("https://th.bing.com/th/id/OIP.yAwZHsnVyP9dvAKnseexRgHaGp?w=233&h=209&c=7&r=0&o=5&pid=1.7"){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(CircleCropTransformation())

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnPokedex.setOnClickListener {
            Toast.makeText(this, "Voçê clicou no botão",
                Toast.LENGTH_SHORT).show()

        }
    }
}