package com.example.pokedex.data

import com.example.network.Output
import com.example.network.parseResponse
import com.example.pokedex.domain.Pokedex

import java.lang.Exception

class PokedexRepositoryIpm(private val api: PokedexApi) : PokedexRepository {
    override suspend fun getPokedex(): Pokedex {
        val respose = api.getPokedex().parseResponse()
        return when (respose) {
            is Output.Success -> respose.value.toPokedex()
            is Output.Failure -> throw GetPokedexException()
        }
    }
}

interface PokedexRepository {
    suspend fun getPokedex(): Pokedex
}

class GetPokedexException : Exception()