package com.example.pokedex.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.GetPokedexUseCase
import com.example.pokedex.domain.asPokedexModel
import com.example.pokedex.presenter.model.PokedexModel
import kotlinx.coroutines.launch

class PokedexViewModel(private val useCase: GetPokedexUseCase) : ViewModel() {

    private val _pokedex = MutableLiveData<StateResponse<PokedexModel>>()
    val pokedex = _pokedex as LiveData<StateResponse<PokedexModel>>

    fun getPokedex() {
        viewModelScope.launch {
            _pokedex.value = StateLoading(true)

            val pokedexModel = useCase.invoke().asPokedexModel()
            fetchAndSetPokemonPhotos(pokedexModel)

            _pokedex.value = StateSuccess(pokedexModel)
            _pokedex.value = StateLoading(false)
        }
    }

    private suspend fun fetchAndSetPokemonPhotos(result: PokedexModel) {
        result.results.forEach { pokemon ->
            val photo = useCase.getPhoto(pokemon.name)
            pokemon.photo = photo?.sprites?.other?.dream_world?.front_default.toString()
            pokemon.height = photo?.height
            pokemon.sprites = photo?.sprites
            pokemon.weight = photo?.weight
            pokemon.id = photo?.id.toString()
            pokemon.color = photo?.specie?.color?.name
        }
    }

}

sealed class StateResponse<T>

open class StateSuccess<T>(val data: T) : StateResponse<T>()
open class StateError<T>(val errorData: Error? = null) : StateResponse<T>()
open class StatePaginatingError<T>(val errorData: Error? = null) : StateResponse<T>()
open class StatePaginatingLoading<T> : StateResponse<T>()
open class StateLoading<T>(val loading: Boolean) : StateResponse<T>()
