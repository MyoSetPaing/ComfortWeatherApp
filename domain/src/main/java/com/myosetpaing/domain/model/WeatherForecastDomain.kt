package com.myosetpaing.domain.model

data class WeatherForecastDomain(
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val countryId: String,
    val name: String,
    val timezone: String,
    val list: List<ForecastDataDomain>,
)

data class ForecastDataDomain(
    val date: String,
    val humidity: Int,
    val wind_mph: Double,
    val maxtemp_c: Double,
    val icon: String
)