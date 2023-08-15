package com.example.network

class BaseResponse<Data>(
    val sprites: Data
)

class Base<Data>(
    val other: Data
)

class Move<Data>(
    val moves: Data
)

class BaseStatus<Data>(
   val stats: List<Data>
)

data class InfoPokemon(
    val weight: Int,
    val height: Int,
    val stats: List<Stat>,
    val moves: Move<MoveDetails>
)

data class MoveDetails(
    val name: String,
    val url: String
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