package com.example.nytimesapp.data.repository

import com.example.nytimesapp.data.mapper.toNyTimes
import com.example.nytimesapp.data.network.NyTimesService
import com.example.nytimesapp.ui.model.NyTimes
import com.example.nytimesapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class NyTimesRepository @Inject constructor(
    val nyTimesService: NyTimesService
) {

    open suspend fun getMostViewedArticless(): Flow<NyTimes> = flow {
        val currentWeather = nyTimesService.getMostViewedArticless(Constants.API_KEY)
        emit(currentWeather.toNyTimes())
    }
}