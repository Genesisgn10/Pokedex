package com.example.utils

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load

fun Context.createSvgImageLoader(): ImageLoader {
    return ImageLoader.Builder(this)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
}

fun ImageView.loadSvgImage(imageUrl: String) {
    this.context.createSvgImageLoader()
    this.load(imageUrl) {
        decoderFactory { result, options, _ ->
            SvgDecoder(result.source, options)
        }
    }
}

fun RecyclerView.addGridSpacingItemDecoration(
    spanCount: Int,
    spacing: Int,
    includeEdge: Boolean
) {
    addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
}
