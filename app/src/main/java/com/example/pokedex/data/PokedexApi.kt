package com.example.pokedex.data

import com.example.network.Base
import com.example.network.BaseResponse
import com.example.network.BaseStatus
import com.example.network.InfoPokemon
import com.example.network.Stat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokedex(): Response<PokedexResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String): Response<BaseResponse<Base<OtherSprites>>>

    @GET("pokemon/{id}")
    suspend fun getPokemon2(@Path("id") id: String): Response<BaseStatus<Stat>>


}