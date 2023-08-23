package com.example.pokedex.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.GetPokedexUseCase
import com.example.pokedex.domain.model.asDetailPokemonModel
import com.example.pokedex.domain.model.asPokedexModel
import com.example.pokedex.presenter.model.DetailPokemonModel
import com.example.pokedex.presenter.model.PokedexModel
import com.example.utils.State
import com.example.utils.StateLoading
import com.example.utils.StateSuccess
import kotlinx.coroutines.launch

class PokedexViewModel(private val useCase: GetPokedexUseCase) : ViewModel() {

    private val _pokedex = MutableLiveData<State<MutableList<DetailPokemonModel>>>()
    val pokedex = _pokedex as LiveData<State<MutableList<DetailPokemonModel>>>

    fun getPokedex() {

        viewModelScope.launch {
            try {
                _pokedex.value = StateLoading(true)
                val pokedexModel = useCase.invoke().asPokedexModel()
                val list = getDetailPokemon(pokedexModel)
                _pokedex.value = StateSuccess(list)
                _pokedex.value = StateLoading(false)
            } catch (ex: Exception) {
                Log.e("me", ex.toString())
            }
        }
    }

    private suspend fun getDetailPokemon(result: PokedexModel): MutableList<DetailPokemonModel> {
        val list = mutableListOf<DetailPokemonModel>()
        result.results.forEach { pokemon ->
            val detailPokemon = useCase.getDetailPokemon(pokemon.name)
            val color = useCase.getColorPokemon(pokemon.name)
            list.add(pokemon.asDetailPokemonModel(color, detailPokemon))
        }
        return list
    }
}
