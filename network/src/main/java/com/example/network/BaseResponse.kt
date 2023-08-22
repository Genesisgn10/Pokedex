package com.example.network

data class Move(
    val move: MoveData
)

data class MoveData(
    val name: String,
    val url: String
)

class Other<Data>(
    val other: Data
)

data class PokemonSprites(
    val dream_world: A
)

data class A(
    val front_default: String
)

data class PokemonSpecies(
    val color: StatDetails,
)

data class InfoPokemon(
    val id: Int,
    val weight: Int,
    val height: Int,
    val stats: List<Stat>,
    val moves: List<Move>,
    val sprites: Other<PokemonSprites>,
    var specie: PokemonSpecies
)

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetails
)

data class StatDetails(
    val name: String,
    val url: String
)