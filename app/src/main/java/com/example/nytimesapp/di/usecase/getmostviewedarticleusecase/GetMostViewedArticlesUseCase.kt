package com.example.nytimesapp.di.usecase.getmostviewedarticleusecase

import com.example.nytimesapp.data.mapper.toNyTimes
import com.example.nytimesapp.data.network.NyTimesService
import com.example.nytimesapp.ui.model.NyTimes
import javax.inject.Inject

class GetMostViewedArticlesUseCase @Inject constructor(
    private val nyTimesService: NyTimesService
) {

    suspend operator fun invoke(
        apiKey: String
    ): Result<NyTimes> {
        return try {
            val response = nyTimesService.getMostViewedArticles(apiKey)
            if (response.isSuccessful) {
                Result.success(response.body()!!.toNyTimes())
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(Exception(e.printStackTrace().toString()))
        }
    }
}