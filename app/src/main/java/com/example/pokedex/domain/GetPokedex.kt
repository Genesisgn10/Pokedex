package com.example.pokedex.domain

import com.example.network.InfoPokemon
import com.example.pokedex.data.OfficialArtworkSprite
import com.example.pokedex.data.PokedexRepository

class GetPokedex(private val repository: PokedexRepository) : GetPokedexUseCase {
    override suspend fun invoke(): Pokedex = try {
        repository.getPokedex()
    } catch (e: Exception) {
        throw Ex()
    }

    override suspend fun getPhoto(name: String): InfoPokemon? {
        return repository.photo(name)
    }

}

interface GetPokedexUseCase {
    suspend operator fun invoke(): Pokedex

    suspend fun getPhoto(name: String): InfoPokemon?
}

class Ex() : Exception()
