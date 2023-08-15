package com.example.pokedex.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.GetPokedexUseCase
import com.example.pokedex.domain.asPokedexModel
import kotlinx.coroutines.launch

class PokedexViewModel(private val useCase: GetPokedexUseCase) : ViewModel() {

    private val _pokedex = MutableLiveData<PokedexModel>()
    val pokedex = _pokedex as LiveData<PokedexModel>

    fun getPokedex() {
        val s = "dsd"
        viewModelScope.launch {
            _pokedex.postValue(useCase.invoke().asPokedexModel())
        }
    }
}