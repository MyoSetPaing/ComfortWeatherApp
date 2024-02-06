package com.myosetpaing.data.remote

import com.myosetpaing.data.Constants.API_KEY
import com.myosetpaing.data.remote.dto.CurrentWeatherDto
import com.myosetpaing.data.remote.dto.ForecastWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ComfortWeatherApi {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") q: String,
        @Query("key") apiKey: String = API_KEY
    ): CurrentWeatherDto

    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("days") days: Int,
        @Query("q") q: String,
        @Query("key") apiKey: String = API_KEY,
    ): ForecastWeatherDto
}