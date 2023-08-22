package com.example.pokedex.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load

import com.example.pokedex.R
import com.example.pokedex.presenter.model.PokemonModel
import com.example.pokedex.presenter.createSvgImageLoader

class AdapterGridList(private val pokemons: List<PokemonModel>) :
    RecyclerView.Adapter<ViewHolderGridList>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGridList {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)

        return ViewHolderGridList(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: ViewHolderGridList, position: Int) {
        val item = pokemons[position]
        holder.bind(item)
    }
}

class ViewHolderGridList(view: View) : RecyclerView.ViewHolder(view) {

    val tvnumber = view.findViewById<TextView>(R.id.tv_numberpokemon)
    val tvname = view.findViewById<TextView>(R.id.tv_namepokemon)
    val ic = view.findViewById<ImageView>(R.id.icon_image)
    val card = view.findViewById<CardView>(R.id.card_item_pokemon)

    fun bind(pokemon: PokemonModel) {
        tvname.text = pokemon.name
        ic.load(pokemon.photo)
        val s = ic.context.createSvgImageLoader()
        ic.load(pokemon.photo) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
        tvnumber.text = pokemon.id
        card.setOnClickListener {
            val bundle = bundleOf("pokemonId" to pokemon)
            it.findNavController().navigate(R.id.detailFragment, bundle)

        }
    }

}
