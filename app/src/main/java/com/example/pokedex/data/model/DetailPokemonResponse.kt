package com.example.pokedex.data.model

import com.example.network.FrontDefault
import com.example.network.Other
import com.example.network.PokemonSprites
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Move
import com.example.pokedex.domain.model.Stat
import com.example.pokedex.domain.model.StatDetails

data class DetailPokemonResponse(
    val id: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<StatResponse> = listOf(),
    val moves: List<MoveResponse> = listOf(),
    val sprites: Other<PokemonSprites> = Other(PokemonSprites(FrontDefault(""))),
    var species: PokemonSpeciesResponse = PokemonSpeciesResponse(StatDetailsResponse("", ""))
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
