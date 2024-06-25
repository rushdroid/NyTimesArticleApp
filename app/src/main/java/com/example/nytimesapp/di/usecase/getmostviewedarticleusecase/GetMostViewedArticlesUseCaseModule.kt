package com.example.nytimesapp.di.usecase.getmostviewedarticleusecase

import com.example.nytimesapp.data.network.NyTimesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetMostViewedArticlesUseCaseModule {

    @Provides
    @Singleton
    fun provideGetMostViewedArticleUseCase(apiService: NyTimesService): GetMostViewedArticlesUseCase {
        return GetMostViewedArticlesUseCase(apiService)
    }
}