package com.example.pokedex.domain

import com.example.pokedex.presenter.model.PokedexModel
import com.example.pokedex.presenter.model.PokemonModel

data class Pokedex(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)

fun Pokedex.asPokedexModel() = PokedexModel(
    count = this.count,
    results = this.results.map { PokemonModel(name = it.name, url = it.url) }
)
