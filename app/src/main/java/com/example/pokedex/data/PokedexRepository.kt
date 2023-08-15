package com.example.pokedex.data

import com.example.network.Output
import com.example.network.parseResponse
import com.example.pokedex.domain.Pokedex

import java.lang.Exception

class PokedexRepositoryIpm(private val api: PokedexApi) : PokedexRepository {
    override suspend fun getPokedex(): Pokedex {
        val response = api.getPokedex().parseResponse()
        return when (response) {
            is Output.Success -> response.value.toPokedex()
            is Output.Failure -> throw GetPokedexException()
        }
    }

    override suspend fun photo(id: String): OfficialArtworkSprite? {
        val response = api.getPokemon(id).parseResponse()
        return when (response) {
            is Output.Success -> response.value.sprites.other.dream_world
            is Output.Failure -> throw GetPokedexException()
        }
    }
}

interface PokedexRepository {
    suspend fun getPokedex(): Pokedex
    suspend fun photo(id: String): OfficialArtworkSprite?
}

class GetPokedexException : Exception()