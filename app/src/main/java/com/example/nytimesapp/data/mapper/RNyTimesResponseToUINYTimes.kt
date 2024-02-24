package com.example.nytimesapp.data.mapper

import com.example.nytimesapp.data.model.RNyTimesArticle
import com.example.nytimesapp.data.model.RResult
import com.example.nytimesapp.ui.model.NyTimes
import com.example.nytimesapp.ui.model.NyTimesArticle

fun RNyTimesArticle.toNyTimes(): NyTimes {
    val nyTimesList = this.results.map {
        it.toNyTimes()
    }
    return NyTimes(
        results = nyTimesList
    )
}

fun RResult.toNyTimes(): NyTimesArticle {
    val imageUrl = if (media.size > 0 && media.first().mediaMetadata.size > 0) {
        media.first().mediaMetadata.last().url
    } else {
        ""
    }
    return NyTimesArticle(
        title = title,
        abstract = abstract,
        byLine = byline,
        publishedDate = publishedDate,
        source = source,
        adxKeywords = adxKeywords,
        articleUrl = url,
        imageUrl = imageUrl
    )
}
