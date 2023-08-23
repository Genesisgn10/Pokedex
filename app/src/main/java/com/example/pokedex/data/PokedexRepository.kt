package com.example.pokedex.data

import com.example.network.Output
import com.example.network.parseResponse
import com.example.pokedex.data.api.PokedexApi
import com.example.pokedex.data.model.asDetailPokemonModel
import com.example.pokedex.data.model.asPokemonSpecie
import com.example.pokedex.data.model.toPokedex
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Pokedex
import com.example.pokedex.domain.model.PokemonSpecies
import java.lang.Exception

class PokedexRepositoryIpm(private val api: PokedexApi) : PokedexRepository {
    override suspend fun getPokedex(): Pokedex {
        return when (val response = api.getPokedex(offset = 0, limit = 12).parseResponse()) {
            is Output.Success -> response.value.toPokedex()
            is Output.Failure -> throw GetPokedexException()
        }
    }

    override suspend fun getDetailPokemon(id: String): DetailPokemon {
        return when (val response = api.getPokemon(id).parseResponse()) {
            is Output.Success -> response.value.asDetailPokemonModel()
            is Output.Failure -> throw GetPokedexException()
        }
    }

    override suspend fun getColorPokemon(id: String): PokemonSpecies {
        return when (val response = api.getSpecie(id = id).parseResponse()) {
            is Output.Success -> response.value.asPokemonSpecie()
            is Output.Failure -> throw GetPokedexException()
        }
    }
}

interface PokedexRepository {
    suspend fun getPokedex(): Pokedex
    suspend fun getDetailPokemon(id: String): DetailPokemon

    suspend fun getColorPokemon(id: String): PokemonSpecies
}

class GetPokedexException : Exception()