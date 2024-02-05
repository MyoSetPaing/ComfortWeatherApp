package com.myosetpaing.domain

import com.myosetpaing.domain.model.ForecastData
import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.model.WeatherForecastDomain

import com.myosetpaing.domain.repository.WeatherForecastRepository
import com.myosetpaing.domain.usecase.GetWeatherForecastUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetWeatherForecastUseCaseTest {


    val data1 = ForecastData(

        humidity = 1,
        rain = 1.0,
        speed = 1.0,
        description = "clearly",
        icon = "10d",
        main = "main",
        windSpeed = 1.0

    )
    val data2 = ForecastData(

        humidity = 1,
        rain = 1.0,
        speed = 1.0,
        description = "clearly",
        icon = "10d",
        main = "main",
        windSpeed = 1.0

    )
    val forecastDomain = WeatherForecastDomain(
        latitude = 10.99,
        longitude = 44.34,
        cnt = 5,
        country = "Myanmar",
        countryId = 1,
        list = listOf(data1, data2),
        message = 1.0,
        name = "",
        population = 100,
        timezone = 1

    )


    private lateinit var weatherForecastRepository: WeatherForecastRepository
    private lateinit var useCase: GetWeatherForecastUseCase


    @Before
    fun setUp() {
        weatherForecastRepository = mockk()
        useCase = GetWeatherForecastUseCase(weatherForecastRepository)
    }

    @Test
    fun `Get  weather forecast,corret name return`(): Unit = runBlocking {
        //prepare for test data
        val testData = flow { emit(Result.Success(forecastDomain)) }
        coEvery {
            weatherForecastRepository.getFiveDayWeatherForecast(
                any(),
                any(),
                any()
            )
        } returns testData

        //do action
        val result = useCase.invoke(GetWeatherForecastUseCase.Input(1.0, 1.0)).first()
        //assert
        assertTrue(result is Result.Success)
        assertFalse(result is Result.Error)
        assertEquals(
            (result as Result.Success).successData.country, forecastDomain.country
        )
    }

    @Test
    fun `Get  weather forecast,error name return`(): Unit = runBlocking {
        //prepare for test data
        val testData = flow { emit(Result.Error(message = "error")) }
        coEvery {
            weatherForecastRepository.getFiveDayWeatherForecast(
                any(),
                any(),
                any()
            )
        } returns testData

        //do action
        val result = useCase.invoke(GetWeatherForecastUseCase.Input(1.0, 1.0)).first()
        //assert
        assertTrue(result is Result.Error)
        assertFalse(result is Result.Success)
        assertEquals((result as Result.Error).message, "error")
    }

}
