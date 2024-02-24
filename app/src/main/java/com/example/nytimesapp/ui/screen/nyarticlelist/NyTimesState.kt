package com.example.nytimesapp.ui.screen.nyarticlelist

import com.example.nytimesapp.ui.model.NyTimes
import com.example.nytimesapp.ui.model.NyTimesArticle

data class NyTimesState(
    val NYTimes: NyTimes = NyTimes(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val isDataFetched: Boolean = false,
    val error: String = "",
    val selectedArticle: NyTimesArticle = NyTimesArticle()
)