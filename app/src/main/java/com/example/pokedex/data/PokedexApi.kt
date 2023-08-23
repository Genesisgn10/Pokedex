package com.example.pokedex.data

import com.example.pokedex.data.model.DetailPokemonResponse
import com.example.pokedex.data.model.PokedexResponse
import com.example.pokedex.data.model.PokemonSpeciesResponse
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
    ): Response<DetailPokemonResponse>

    @GET("pokemon-species/{id}")
    suspend fun getSpecie(
        @Path("id") id: String
    ): Response<PokemonSpeciesResponse>

}