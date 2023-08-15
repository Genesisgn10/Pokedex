package com.example.pokedex.presenter

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder

fun Context.createSvgImageLoader(): ImageLoader {
    return ImageLoader.Builder(this)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
}