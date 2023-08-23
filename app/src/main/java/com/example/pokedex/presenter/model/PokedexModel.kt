package com.example.pokedex.presenter.model

import com.example.network.Other
import com.example.network.PokemonSprites
import java.io.Serializable

data class PokedexModel(
    val count: Int,
    val results: List<PokemonBase>
)

data class PokemonBase(
    val name: String,
    val url: String
)

data class StatModel(
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetailsModel
)

data class MoveModel(
    val move: StatDetailsModel
)

data class StatDetailsModel(
    val name: String,
    val url: String
)

data class DetailPokemonModel(
    var id: String = "",
    val name: String = "",
    val url: String = "",
    var photo: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    val stats: List<StatModel> = listOf(),
    val moves: List<MoveModel> = listOf(),
    var sprites: Other<PokemonSprites>,
    var color: String = ""
) : Serializable