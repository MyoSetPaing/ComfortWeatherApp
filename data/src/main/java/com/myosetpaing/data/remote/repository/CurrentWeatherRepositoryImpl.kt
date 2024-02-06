package com.myosetpaing.data.remote.repository


import com.myosetpaing.data.di.IoDispatcher
import com.myosetpaing.data.local.ComfortWeatherDatabase
import com.myosetpaing.data.mapper.toCurrentWeatherDomain
import com.myosetpaing.data.mapper.toCurrentWeatherEntity
import com.myosetpaing.data.remote.ComfortWeatherApi
import com.myosetpaing.data.remote.safeApiCall
import com.myosetpaing.domain.internet.InternetConnectionChecker
import com.myosetpaing.domain.model.CurrentWeatherDomain
import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val weatherApi: ComfortWeatherApi,
    private val weatherAppDatabase: ComfortWeatherDatabase,
    private val internetConnectionChecker: InternetConnectionChecker,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) :
    CurrentWeatherRepository {
    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Flow<Result<CurrentWeatherDomain>> {

        if (internetConnectionChecker.isNetworkAvailable()) {
            val response = safeApiCall(coroutineDispatcher) {
                weatherApi.getCurrentWeather(q="${lat},${lon}")
            }
            if (response is Result.Success) {
                weatherAppDatabase.weatherDao()
                    .upsertCurrentWeather(response.successData.toCurrentWeatherEntity())

            }
            return weatherAppDatabase.weatherDao().getCurrentWeatherLocal().map {value-> Result.Success(value.toCurrentWeatherDomain())
            }
        } else {
            return weatherAppDatabase.weatherDao().getCurrentWeatherLocal().map {
                Result.Success(it.toCurrentWeatherDomain())
            }
        }
    }
}