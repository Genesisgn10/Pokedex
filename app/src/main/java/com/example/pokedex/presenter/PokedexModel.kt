package com.example.pokedex.presenter

data class PokedexModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonModel>
)

data class PokemonModel(
    val name: String,
    val url: String,
    var photo : String = ""
)