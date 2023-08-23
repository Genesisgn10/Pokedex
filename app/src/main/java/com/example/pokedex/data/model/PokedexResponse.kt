package com.example.pokedex.data.model

import com.example.network.FrontDefault
import com.example.network.Other
import com.example.network.PokemonSprites
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Move
import com.example.pokedex.domain.model.Pokedex
import com.example.pokedex.domain.model.PokemonSpecies
import com.example.pokedex.domain.model.Stat
import com.example.pokedex.domain.model.StatDetails
import com.example.pokedex.presenter.model.PokemonBase

data class PokedexResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResponse>
)

data class PokemonResponse(
    val name: String,
    val url: String
)

data class MoveResponse(
    val move: StatDetailsResponse
)

data class StatDetailsResponse(
    val name: String = "",
    val url: String = ""
)

data class DetailPokemonResponse(
    val id: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<StatResponse> = listOf(),
    val moves: List<MoveResponse> = listOf(),
    val sprites: Other<PokemonSprites> = Other(PokemonSprites(dream_world = FrontDefault(""))),
    var species: PokemonSpeciesResponse = PokemonSpeciesResponse(StatDetailsResponse("", ""))
)

data class PokemonSpeciesResponse(
    val color: StatDetailsResponse?,
)

data class StatResponse(
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetailsResponse
)

fun PokedexResponse.toPokedex() = Pokedex(
    count = this.count ?: 0,
    next = this.next ?: "",
    previous = this.previous ?: "",
    results = this.results.map { PokemonBase(name = it.name, url = it.url) }
)

fun DetailPokemonResponse.asDetailPokemonModel() = DetailPokemon(
    id = this.id,
    weight = this.weight,
    height = this.height,
    sprites = this.sprites,
    moves = this.moves.map { Move(StatDetails(url = it.move.url, name = it.move.name)) },
    stats = this.stats.map {
        Stat(
            baseStat = it.baseStat,
            effort = it.effort,
            stat = it.stat.asStatDetails()
        )
    }
)

fun PokemonSpeciesResponse.asPokemonSpecie() = PokemonSpecies(
    color = this.color?.asStatDetails() ?: StatDetails(name = "", url = "")
)

fun StatDetailsResponse.asStatDetails() = StatDetails(
    name = this.name ?: "",
    url = this.url ?: ""
)