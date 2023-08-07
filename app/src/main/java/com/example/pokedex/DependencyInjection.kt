package com.example.pokedex

import com.example.network.Service
import org.koin.dsl.module

val appModule = module {

    single { Service().createService(PokedexApi::class.java) }

}