package com.example.pokedex.presenter.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import coil.decode.SvgDecoder
import coil.load
import com.example.pokedex.R
import com.example.pokedex.databinding.DetailFragmentBinding
import com.example.pokedex.presenter.MainActivity

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

        (activity as MainActivity).showToolbar(false)

        args.pokemonId.height

        val string = args.pokemonId.weight?.toFloat().toString() + " kg"

        binding.tvWeight.text = string
        val colorToResource = mapOf(
            "green" to PokemonColor.GREEN.colorRes,
            "yellow" to PokemonColor.YELLOW.colorRes,
            "red" to PokemonColor.RED.colorRes,
            "blue" to PokemonColor.BLUE.colorRes,
            "white" to PokemonColor.WHITE.colorRes,
            "pink" to PokemonColor.RED.colorRes
        )

        val color = args.pokemonId.color.toString()
        val colorRes = colorToResource[color]

        colorRes?.let {
            binding.back.setBackgroundColor(
                ContextCompat.getColor(requireContext(), it)
            )
        }

        binding.imageView.load(args.pokemonId.photo) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
    }


}

enum class PokemonColor(@ColorRes val colorRes: Int) {
    GREEN(R.color.green),
    BLUE(R.color.blue),
    BROWN(R.color.green),
    GRAY(R.color.green),
    YELLOW(R.color.yellow),
    PINK(R.color.green),
    PURPLE(R.color.green),
    RED(R.color.red),
    WHITE(R.color.green),
}