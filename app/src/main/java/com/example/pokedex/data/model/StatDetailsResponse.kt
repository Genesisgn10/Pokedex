package com.example.pokedex.data.model

import com.example.pokedex.domain.model.StatDetails

data class StatDetailsResponse(
    val name: String = "",
    val url: String = ""
)

fun StatDetailsResponse.asStatDetails() = StatDetails(
    name = this.name,
    url = this.url
)
