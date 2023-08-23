package com.example.pokedex.data.model

import com.example.pokedex.domain.model.PokemonSpecies
import com.example.pokedex.domain.model.StatDetails

data class PokemonSpeciesResponse(
    val color: StatDetailsResponse?,
)

fun PokemonSpeciesResponse.asPokemonSpecie() = PokemonSpecies(
    color = this.color?.asStatDetails() ?: StatDetails(name = "", url = "")
)