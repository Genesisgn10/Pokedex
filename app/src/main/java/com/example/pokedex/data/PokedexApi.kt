package com.example.pokedex.data

import com.example.network.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokedex(): Response<PokedexResponse>

}