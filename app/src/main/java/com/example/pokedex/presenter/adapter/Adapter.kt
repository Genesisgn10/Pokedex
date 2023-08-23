package com.example.pokedex.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.utils.loadSvgImage

class Adapter(private val pokemons: List<DetailPokemonModel>) :
    RecyclerView.Adapter<ViewHolderGridList>() {

    private val originalPokemons: List<DetailPokemonModel> = pokemons.toMutableList()
    private var filteredPokemons: List<DetailPokemonModel> = pokemons
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGridList {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)

        return ViewHolderGridList(view)
    }

    override fun getItemCount(): Int {
        return filteredPokemons.size
    }

    override fun onBindViewHolder(holder: ViewHolderGridList, position: Int) {
        val item = filteredPokemons[position]
        holder.bind(item)
    }

    fun filterPokemonsByName(name: String) {
        filteredPokemons = originalPokemons.filter { it.name.contains(name, true) }
        notifyDataSetChanged()
    }
}

class ViewHolderGridList(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNumber: AppCompatTextView = view.findViewById(R.id.tv_numberpokemon)
    private val tvName: TextView = view.findViewById(R.id.tv_namepokemon)
    private val iconImage: ImageView = view.findViewById(R.id.icon_image)
    private val cardItem: CardView = view.findViewById(R.id.card_item_pokemon)

    fun bind(pokemon: DetailPokemonModel) {
        tvName.text = pokemon.name
        loadSvgImage(iconImage, pokemon.photo)
        tvNumber.text = "# ${pokemon.id}"
        setupCardClickListener(pokemon)
    }

    private fun loadSvgImage(imageView: ImageView, imageUrl: String) {
        imageView.loadSvgImage(imageUrl)
    }

    private fun setupCardClickListener(pokemon: DetailPokemonModel) {
        cardItem.setOnClickListener {
            val bundle = bundleOf("pokemonId" to pokemon)
            it.findNavController().navigate(R.id.detailFragment, bundle)
        }
    }
}