package com.example.nytimesapp.data.network

import com.example.nytimesapp.data.model.RNyTimesArticle
import retrofit2.http.GET
import retrofit2.http.Query

interface NyTimesService {

    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun getMostViewedArticless(
        @Query("api-key") apiKey: String
    ): RNyTimesArticle
}
