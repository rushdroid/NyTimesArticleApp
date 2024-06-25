package com.example.nytimesapp.ui.screen.nyarticlelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesapp.BuildConfig
import com.example.nytimesapp.di.usecase.getmostviewedarticleusecase.GetMostViewedArticlesUseCase
import com.example.nytimesapp.ui.model.NyTimesArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NyTimesViewModel @Inject constructor(
    private val getMostViewedArticlesUseCase: GetMostViewedArticlesUseCase
) : ViewModel() {

    private val _nyTimesState = MutableStateFlow(NyTimesState())
    val nyTimesState: StateFlow<NyTimesState> = _nyTimesState

    fun getMostViewedArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _nyTimesState.value = _nyTimesState.value.copy(
                isLoading = true
            )
            val res = getMostViewedArticlesUseCase.invoke(
                apiKey = BuildConfig.API_KEY
            )
            res.fold(onSuccess = {
                _nyTimesState.value = _nyTimesState.value.copy(
                    NYTimes = it,
                    isLoading = false,
                    error = "",
                    hasError = false,
                    isDataFetched = true
                )
            }, onFailure = {
                _nyTimesState.value = _nyTimesState.value.copy(
                    isLoading = false,
                    error = "Failed to get the data",
                    hasError = true,
                    isDataFetched = false
                )
            })
        }
    }

    fun selectArticle(article: NyTimesArticle) {
        _nyTimesState.value = _nyTimesState.value.copy(
            selectedArticle = article
        )
    }
}