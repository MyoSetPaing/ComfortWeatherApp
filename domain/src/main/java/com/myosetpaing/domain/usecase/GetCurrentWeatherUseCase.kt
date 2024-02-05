package com.myosetpaing.domain.usecase

import com.myosetpaing.domain.model.CurrentWeatherDomain
import com.myosetpaing.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.myosetpaing.domain.model.Result

class GetCurrentWeatherUseCase @Inject internal constructor(private val currentWeatherRepository: CurrentWeatherRepository) :
    SuspendUseCase<GetCurrentWeatherUseCase.Input, Flow<Result<CurrentWeatherDomain>>> {
    override suspend fun invoke(input: Input): Flow<Result<CurrentWeatherDomain>> {
        return currentWeatherRepository.getCurrentWeather(input.lat, input.lon)
    }

    class Input(val lat: Double, val lon: Double)
}