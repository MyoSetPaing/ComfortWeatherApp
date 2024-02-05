package com.myosetpaing.data.remote

import com.myosetpaing.data.Constants.API_KEY
import com.myosetpaing.data.remote.dto.CurrentWeatherDto
import com.myosetpaing.data.remote.dto.ForecastWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ComfortWeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY
    ): CurrentWeatherDto

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("cnt") cnt: Int
    ): ForecastWeatherDto
}