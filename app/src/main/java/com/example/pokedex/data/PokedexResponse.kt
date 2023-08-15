package com.example.pokedex.data

import com.example.pokedex.domain.Pokedex
import com.example.pokedex.domain.Pokemon

data class PokedexResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResponse>
)

data class PokemonResponse(
    val name: String,
    val url: String
)

fun PokedexResponse.toPokedex() = Pokedex(
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.map { Pokemon(name = it.name, url = it.url) }
)