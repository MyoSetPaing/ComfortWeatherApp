package com.myosetpaing.domain.repository

import com.myosetpaing.domain.model.CurrentWeatherDomain
import com.myosetpaing.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    //To get Current Weather Data From single source
    suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Result<CurrentWeatherDomain>>
}