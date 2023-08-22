package com.example.pokedex.data

import com.example.network.InfoPokemon
import com.example.network.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokedex(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<PokedexResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): Response<InfoPokemon>

    @GET("pokemon-species/{id}")
    suspend fun getSpacie(
        @Path("id") id: String
    ): Response<PokemonSpecies>

}