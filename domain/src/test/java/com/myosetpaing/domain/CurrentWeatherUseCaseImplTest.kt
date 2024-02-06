package com.myosetpaing.domain

import com.myosetpaing.domain.model.CurrentWeatherDomain
import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.repository.CurrentWeatherRepository
import com.myosetpaing.domain.usecase.GetCurrentWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class CurrentWeatherUseCaseImplTest {

    val data = CurrentWeatherDomain(

        latitude = 10.99,
        longitude = 44.34,
        id = "3163858",
        humidity = 64,
        temp = 298.48,

        country = "IT",
        name = "Zocca",
        timezone = "32424",
        windSpeed = 0.62,
        description = "clear",
        icon = "10d",

    )
    private lateinit var currentWeatherRepository: CurrentWeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        currentWeatherRepository = mockk()
        useCase = GetCurrentWeatherUseCase(currentWeatherRepository)
    }

    @Test
    fun `Get current weather,corret name return`(): Unit = runBlocking {
        //prepare for test data
        val testData = flow { emit(Result.Success(data)) }
        coEvery { currentWeatherRepository.getCurrentWeather(any(), any()) } returns testData

        //do action
        val result = useCase.invoke(GetCurrentWeatherUseCase.Input(1.0, 1.0)).first()

        //assert
        assertTrue(result is Result.Success)
        assertFalse(result is Result.Error)
        assertEquals((result as Result.Success).successData.name, data.name)

    }

    @Test
    fun `Get current weather,incorret name return`(): Unit = runBlocking {
        //prepare for test data
        val testData = flow { emit(Result.Error(message = "error")) }
        coEvery { currentWeatherRepository.getCurrentWeather(any(), any()) } returns testData

        //do action
        val result = useCase.invoke(GetCurrentWeatherUseCase.Input(1.0, 1.0)).first()

        //assert
        assertTrue(result is Result.Error)
        assertFalse(result is Result.Success)
        assertEquals((result as Result.Error).message, "error")
    }
}