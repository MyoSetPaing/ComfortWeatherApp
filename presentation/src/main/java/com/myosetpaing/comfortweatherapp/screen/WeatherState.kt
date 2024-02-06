package com.myosetpaing.comfortweatherapp.screen

import com.myosetpaing.domain.model.CurrentWeatherDomain
import com.myosetpaing.domain.model.WeatherForecastDomain

data class WeatherState(
    val weatherCurrentInfo: CurrentWeatherDomain? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val weatherForecastInfo: WeatherForecastDomain? = null,

    )
