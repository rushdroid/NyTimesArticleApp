package com.example.nytimesapp.ui.model

data class NyTimes(
    val results: List<NyTimesArticle> = listOf()
)

data class NyTimesArticle(
    val title: String = "",
    val byLine: String = "",
    val abstract: String = "",
    val publishedDate: String = "",
    val imageUrl: String = "",
    val articleUrl: String = "",
    val adxKeywords: String = "",
    val subSection: String = "",
    val source: String = ""
)