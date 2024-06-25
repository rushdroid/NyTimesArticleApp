package com.example.nytimesapp.ui.screen.nyarticlelist

import com.example.nytimesapp.ui.model.NyTimes
import com.example.nytimesapp.ui.model.NyTimesArticle

data class NyTimesState(
    val NYTimes: NyTimes = NyTimes(),
    val isLoading: Boolean = true,
    val isDataFetched: Boolean = false,
    val error: String = "",
    val selectedArticle: NyTimesArticle = NyTimesArticle()
){
    override fun hashCode(): Int {
        return super.hashCode()
    }
}