package com.example.pokedex.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokedex.databinding.DetailFragmentBinding
import com.example.pokedex.presenter.MainActivity
import com.example.pokedex.presenter.constants.PokemonColor
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.utils.loadSvgImage

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding

    private val args: DetailFragmentArgs by navArgs()

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
        val weightString = pokemonId.weight?.toFloat()?.toString()?.plus(" kg") ?: ""

        with(binding) {
            bindPokemonInfo(pokemonId, weightString)
            bindBackgroundColor(pokemonId.color.toString())
            bindSvgImage(pokemonId.photo)
        }
    }

    private fun DetailFragmentBinding.bindPokemonInfo(
        pokemonId: DetailPokemonModel,
        weightString: String
    ) {
        tvName.text = pokemonId.name
        tvWeight.text = weightString
    }

    private fun DetailFragmentBinding.bindBackgroundColor(colorString: String) {
        val colorRes = getColorResourceForColorString(colorString)
        colorRes?.let { color ->
            val colorResource = ContextCompat.getColor(root.context, color)
            back.setBackgroundColor(colorResource)
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

