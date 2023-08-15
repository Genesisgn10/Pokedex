package com.example.pokedex.data

import com.example.network.Output
import com.example.network.parseResponse
import com.example.pokedex.domain.Pokedex

import java.lang.Exception

class PokedexRepositoryIpm(private val api: PokedexApi) : PokedexRepository {
    override suspend fun getPokedex(): Pokedex {
       val t =  api.getPokemon2("1").parseResponse()
        val respose = api.getPokedex().parseResponse()
        return when (respose) {
            is Output.Success -> respose.value.toPokedex()
            is Output.Failure -> throw GetPokedexException()
        }
    }

    override suspend fun photo(id: String): OfficialArtworkSprite? {
        val respose = api.getPokemon(id).parseResponse()
        return when (respose) {
            is Output.Success -> respose.value.sprites.other.dream_world
            is Output.Failure -> throw GetPokedexException()
        }
    }
}

interface PokedexRepository {
    suspend fun getPokedex(): Pokedex
    suspend fun photo(id : String) : OfficialArtworkSprite?
}

class GetPokedexException : Exception()