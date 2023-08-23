package com.example.pokedex.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.pokedex.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import com.example.pokedex.presenter.fragment.DialogFragment
import com.example.pokedex.presenter.fragment.ToolbarTextListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var toolbarTextListener: ToolbarTextListener? = null


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

        binding.toolbar.textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nada a fazer antes da mudança de texto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Chame a função de filtro do adaptador aqui
                onToolbarTextChanged(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                // Nada a fazer depois da mudança de texto
            }
        })
    }

    fun setToolbarTextListener(listener: ToolbarTextListener) {
        toolbarTextListener = listener
    }

    // Quando o texto na Toolbar for editado
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