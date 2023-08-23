package com.example.pokedex.data.model

import com.example.pokedex.domain.model.Pokedex
import com.example.pokedex.presenter.model.PokemonBase

data class PokedexResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResponse>
)

fun PokedexResponse.toPokedex() = Pokedex(
    count = this.count ?: 0,
    next = this.next ?: "",
    previous = this.previous ?: "",
    results = this.results.map { PokemonBase(name = it.name, url = it.url) }
)