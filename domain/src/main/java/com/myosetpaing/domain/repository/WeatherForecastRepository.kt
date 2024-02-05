package com.myosetpaing.domain.repository

import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.model.WeatherForecastDomain
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    //To get Forecast Weather Data From single source
    suspend fun getFiveDayWeatherForecast(
        lat: Double,
        lon: Double,
        cnt: Int
    ): Flow<Result<WeatherForecastDomain>>
}