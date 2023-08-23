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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.utils.loadSvgImage

class PokemonAdapter(private val pokemons: List<DetailPokemonModel>) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    private val filteredPokemons: MutableList<DetailPokemonModel> = pokemons.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = parent.inflate(R.layout.grid_layout_list_item)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int = filteredPokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(filteredPokemons[position])
    }

    fun filterPokemonsByName(name: String) {
        val newFilteredPokemons = pokemons.filter { it.name.contains(name, true) }
        applyNewFilter(newFilteredPokemons)
    }

    private fun applyNewFilter(newFilteredPokemons: List<DetailPokemonModel>) {
        val diffResult = calculateDiff(PokemonDiffCallback(filteredPokemons, newFilteredPokemons))
        filteredPokemons.clear()
        filteredPokemons.addAll(newFilteredPokemons)
        diffResult.dispatchUpdatesTo(this)
    }
}

class PokemonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val tvNumber: AppCompatTextView = view.findViewById(R.id.tv_numberpokemon)
    private val tvName: TextView = view.findViewById(R.id.tv_namepokemon)
    private val iconImage: ImageView = view.findViewById(R.id.icon_image)
    private val cardItem: CardView = view.findViewById(R.id.card_item_pokemon)

    fun bind(pokemon: DetailPokemonModel) {
        resetViewState()
        with(pokemon) {
            tvName.text = name
            iconImage.loadSvgImage(photo)
            tvNumber.text = "# $id"
            setupCardClickListener(this)
        }
    }

    private fun resetViewState() {
        cardItem.setOnClickListener(null)
    }

    private fun setupCardClickListener(pokemon: DetailPokemonModel) {
        cardItem.setOnClickListener {
            val bundle = bundleOf("pokemonId" to pokemon)
            it.findNavController().navigate(R.id.detailFragment, bundle)
        }
    }
}

class PokemonDiffCallback(
    private val oldList: List<DetailPokemonModel>,
    private val newList: List<DetailPokemonModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

// Extension function to simplify inflating views
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

// Extension function to simplify loading SVG images
fun ImageView.loadSvgImage(imageUrl: String) {
    // Implement your SVG image loading logic here
}