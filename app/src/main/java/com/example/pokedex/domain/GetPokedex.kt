package com.example.pokedex.domain

import com.example.pokedex.data.PokedexRepository
import com.example.pokedex.presenter.PokedexModel

class GetPokedex(private val repository: PokedexRepository) : GetPokedexUseCase {
    override suspend fun invoke(): Pokedex = try {
        repository.getPokedex()
    } catch (e: Exception) {
        throw Ex()
    }

}

interface GetPokedexUseCase {
    suspend operator fun invoke(): Pokedex
}

class Ex() : Exception()
