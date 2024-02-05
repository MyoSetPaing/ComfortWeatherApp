package com.myosetpaing.domain.usecase

import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.model.WeatherForecastDomain
import com.myosetpaing.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(private val weatherForecastRepository: WeatherForecastRepository) :
    SuspendUseCase<GetWeatherForecastUseCase.Input, Flow<Result<WeatherForecastDomain>>> {

    class Input(val lat: Double, val lon: Double)

    override suspend fun invoke(input: Input): Flow<Result<WeatherForecastDomain>> {
        return weatherForecastRepository.getFiveDayWeatherForecast(input.lat, input.lon, 5)
    }
}