package com.example.pokedex.data

import com.example.network.InfoPokemon
import com.example.network.Output
import com.example.network.PokemonSpecies
import com.example.network.parseResponse
import com.example.pokedex.domain.Pokedex

import java.lang.Exception

class PokedexRepositoryIpm(private val api: PokedexApi) : PokedexRepository {
    override suspend fun getPokedex(): Pokedex {
        return when (val response = api.getPokedex(offset = 0, limit = 12).parseResponse()) {
            is Output.Success -> response.value.toPokedex()
            is Output.Failure -> throw GetPokedexException()
        }
    }

    override suspend fun photo(id: String): InfoPokemon {
        return when (val response = api.getPokemon(id).parseResponse()) {
            is Output.Success -> {
                response.value.specie = get(id)
                return response.value
            }
            is Output.Failure -> throw GetPokedexException()
        }

    }

    private suspend fun get(id: String): PokemonSpecies {
       return  when(val response = api.getSpacie(id = id).parseResponse()){
           is Output.Success -> {response.value}
           is Output.Failure -> throw GetPokedexException()
       }
    }
}

interface PokedexRepository {
    suspend fun getPokedex(): Pokedex
    suspend fun photo(id: String): InfoPokemon?
}

class GetPokedexException : Exception()