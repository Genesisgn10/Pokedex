package com.example.pokedex.presenter.model

import com.example.network.Move
import com.example.network.Other
import com.example.network.PokemonSprites
import com.example.network.Stat
import java.io.Serializable

data class PokedexModel(
    val count: Int,
    val results: List<PokemonModel>
)

data class PokemonModel(
    var id: String? = null,
    val name: String,
    val url: String,
    var photo: String = "",
    var weight: Int? = null,
    var height: Int? = null,
    val stats: List<Stat>? = null,
    val moves: List<Move>? = null,
    var sprites: Other<PokemonSprites>? = null,
    var color : String? = null
) : Serializable