package com.example.pokedex.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.pokedex.presenter.fragment.DialogFragment
import com.example.pokedex.presenter.fragment.ToolbarTextListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var toolbarTextListener: ToolbarTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize splash screen transition
        installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
    }

    private fun setupToolbar() {
        with(binding) {
            setSupportActionBar(toolbar.myToolbar)
            toolbar.floatingActionButton.setOnClickListener { showDialog() }
            toolbar.textInput.doOnTextChanged { text, _, _, _ ->
                onToolbarTextChanged(text.toString())
            }
        }
    }

    fun setToolbarTextListener(listener: ToolbarTextListener) {
        toolbarTextListener = listener
    }

    private fun onToolbarTextChanged(text: String) {
        toolbarTextListener?.onToolbarTextChanged(text)
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