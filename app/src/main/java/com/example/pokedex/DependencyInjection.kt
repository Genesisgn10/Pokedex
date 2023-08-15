package com.example.pokedex

import com.example.network.Service
import com.example.pokedex.data.PokedexApi
import com.example.pokedex.data.PokedexRepository
import com.example.pokedex.data.PokedexRepositoryIpm
import com.example.pokedex.domain.GetPokedex
import com.example.pokedex.domain.GetPokedexUseCase
import com.example.pokedex.presenter.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Service().createService(PokedexApi::class.java) }

    factory<PokedexRepository> { PokedexRepositoryIpm(api = get()) }

    factory<GetPokedexUseCase> { GetPokedex(repository = get()) }

    factory { PokedexViewModel(get()) }

}