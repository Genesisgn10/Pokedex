package com.example.pokedex.domain.model

import com.example.network.Other
import com.example.network.PokemonSprites
import com.example.pokedex.presenter.model.PokedexModel
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.pokedex.presenter.model.PokemonBase

data class Pokedex(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonBase>
)

data class Move(
    val move: StatDetails
)

data class DetailPokemon(
    val id: Int,
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<Stat>,
    val moves: List<Move>,
    val sprites: Other<PokemonSprites>,
)

data class PokemonSpecies(
    val color: StatDetails,
)

data class StatDetails(
    val name: String,
    val url: String
)

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetails
)

fun Pokedex.asPokedexModel() = PokedexModel(
    count = this.count,
    results = this.results.map { PokemonBase(name = it.name, url = it.url) }
)

fun PokemonBase.asDetailPokemonModel(photo: PokemonSpecies, detailPokemon: DetailPokemon) =
    DetailPokemonModel(
        name = this.name,
        url = this.url,
        id = detailPokemon.id.toString(),
        color = photo.color.name,
        weight = detailPokemon.weight,
        sprites = detailPokemon.sprites,
        height = detailPokemon.height,
        photo = detailPokemon.sprites.other.dream_world.front_default
    )