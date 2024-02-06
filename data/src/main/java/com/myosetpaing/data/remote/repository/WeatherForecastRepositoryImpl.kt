package com.myosetpaing.data.remote.repository

import com.myosetpaing.data.di.IoDispatcher
import com.myosetpaing.data.local.ComfortWeatherDatabase
import com.myosetpaing.data.mapper.toWeatherForecastDomain
import com.myosetpaing.data.mapper.toWeatherForecastEntity
import com.myosetpaing.data.remote.ComfortWeatherApi
import com.myosetpaing.data.remote.safeApiCall
import com.myosetpaing.domain.internet.InternetConnectionChecker
import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.model.WeatherForecastDomain
import com.myosetpaing.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherApi: ComfortWeatherApi,
    private val weatherAppDatabase: ComfortWeatherDatabase,
    private val internetConnectionChecker: InternetConnectionChecker,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : WeatherForecastRepository {
    override suspend fun getFiveDayWeatherForecast(
        lat: Double,
        lon: Double,
        cnt: Int
    ): Flow<Result<WeatherForecastDomain>> {

        if (internetConnectionChecker.isNetworkAvailable()) {
            val response = safeApiCall(coroutineDispatcher) {
                weatherApi.getWeatherForecast(q = "$lat,$lon", days = cnt)
            }
            if (response is Result.Success) {
                weatherAppDatabase.weatherDao()
                    .upsertWeatherForecast(response.successData.toWeatherForecastEntity())

            }
            return weatherAppDatabase.weatherDao().getWeatherForecastLocal().map {
                Result.Success(it.toWeatherForecastDomain())
            }
        } else {
            return weatherAppDatabase.weatherDao().getWeatherForecastLocal().map {
                Result.Success(it.toWeatherForecastDomain())
            }
        }
    }
}