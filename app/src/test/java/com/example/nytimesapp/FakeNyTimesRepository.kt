package com.example.nytimesapp

import com.example.nytimesapp.data.network.NyTimesService
import com.example.nytimesapp.data.repository.NyTimesRepository
import com.example.nytimesapp.ui.model.NyTimes
import com.example.nytimesapp.ui.model.NyTimesArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeNyTimesRepository(nyTimesService: NyTimesService) : NyTimesRepository(nyTimesService) {

    var isSuccess = true
    override suspend fun getMostViewedArticless(): Flow<NyTimes> {
        if (isSuccess.not()) {
            throw Exception("Error while fetching data")
        }
        return flowOf(
            NyTimes(
                listOf(
                    NyTimesArticle(
                        title = "Test",
                        source = "Test Source"
                    )
                )
            )
        )
    }

}
