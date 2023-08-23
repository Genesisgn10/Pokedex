package com.example.pokedex.presenter.fragment

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.DetailFragmentBinding
import com.example.pokedex.presenter.MainActivity
import com.example.pokedex.presenter.constants.PokemonColor
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.utils.loadSvgImage

class DetailPokemonFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private val args: DetailPokemonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).showToolbar(isVisible = false)
        setupUI()
    }

    private fun setupUI() {
        val pokemonId = args.pokemonId
        val weightString = pokemonId.weight.toFloat().toString().plus(" kg")

        with(binding) {
            bindPokemonInfo(pokemonId, weightString)
            bindBackgroundColor(pokemonId.color)
            bindSvgImage(pokemonId.photo)
            bindOnclick()
        }
    }

    private fun DetailFragmentBinding.bindOnclick() {
        viewBackButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun DetailFragmentBinding.bindPokemonInfo(
        pokemonId: DetailPokemonModel,
        weightString: String
    ) {
        tvName.text = pokemonId.name
        tvWeight.text = weightString
        tvHeight.text = pokemonId.weight.toString() + "m"
    }

    private fun DetailFragmentBinding.bindBackgroundColor(colorString: String) {
        val colorRes = getColorResourceForColorString(colorString)
        colorRes?.let { color ->
            val colorResource = ContextCompat.getColor(root.context, color)
            val progressTintColor = ColorStateList.valueOf(colorResource)
            back.setBackgroundColor(colorResource)
            viewBaseStats.setBackgroundColor(colorResource)
            viewHeiMo.setBackgroundColor(colorResource)
            viewWeiHei.setBackgroundColor(colorResource)
            pbAtk.progressTintList = progressTintColor
            pbDef.progressTintList = progressTintColor
            pbHp.progressTintList = progressTintColor
            pbSdef.progressTintList = progressTintColor
            pbSpd.progressTintList = progressTintColor
            pbSatk.progressTintList = progressTintColor
            requireActivity().window.statusBarColor = colorResource
        }
    }

    private fun getColorResourceForColorString(colorString: String): Int? {
        val colorToResource = mapOf(
            "green" to PokemonColor.GREEN,
            "yellow" to PokemonColor.YELLOW,
            "red" to PokemonColor.RED,
            "blue" to PokemonColor.BLUE,
            "white" to PokemonColor.WHITE,
            "pink" to PokemonColor.PINK
        )
        return colorToResource[colorString]?.colorRes
    }

    private fun DetailFragmentBinding.bindSvgImage(url: String) {
        imageView.loadSvgImage(url)
    }
}

