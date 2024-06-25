package com.example.nytimesapp

import com.example.nytimesapp.data.network.NyTimesService
import com.example.nytimesapp.ui.screen.nyarticlelist.NyTimesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NyTimeViewModelTest {

    private lateinit var viewModel: NyTimesViewModel
    private lateinit var nyTimeService: NyTimesService
//    private lateinit var fakeNyTimesRepository: FakeNyTimesRepository

    @Before
    fun setup() {
        nyTimeService = mock<NyTimesService>()
//        fakeNyTimesRepository = FakeNyTimesRepository(nyTimeService)
    }


    @Test
    fun getMostViewedArticles_success() = runBlocking {
//        fakeNyTimesRepository.isSuccess = true
        viewModel.getMostViewedArticles()
        delay(5000)
        assert(viewModel.nyTimesState.value.NYTimes.results.isNotEmpty())
    }
}