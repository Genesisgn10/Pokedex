package com.example.pokedex.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokedex.R

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

    fun bind(pokemon: PokemonModel) {
        tvname.text = pokemon.name
        ic.load(pokemon.url)
    }

}