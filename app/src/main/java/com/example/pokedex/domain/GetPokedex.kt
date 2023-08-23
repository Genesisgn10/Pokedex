package com.example.pokedex.domain

import com.example.pokedex.data.PokedexRepository
import com.example.pokedex.domain.model.DetailPokemon
import com.example.pokedex.domain.model.Pokedex
import com.example.pokedex.domain.model.PokemonSpecies

class GetPokedex(private val repository: PokedexRepository) : GetPokedexUseCase {
    override suspend fun invoke(): Pokedex = try {
        repository.getPokedex()
    } catch (e: Exception) {
        throw Ex()
    }

    override suspend fun getDetailPokemon(name: String): DetailPokemon {
        return repository.getDetailPokemon(name)
    }

    override suspend fun getColorPokemon(name: String) = repository.getColorPokemon(name)

}

interface GetPokedexUseCase {
    suspend operator fun invoke(): Pokedex
    suspend fun getDetailPokemon(name: String): DetailPokemon
    suspend fun getColorPokemon(name: String): PokemonSpecies
}

class Ex() : Exception()
