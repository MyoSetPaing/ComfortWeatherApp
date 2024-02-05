package com.myosetpaing.domain.model

data class WeatherForecastDomain(
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val countryId: Int,
    val name: String,
    val population: Int,
    val timezone: Int,
    val cnt: Int,
    val list: List<ForecastData>,
    val message: Double
)

data class ForecastData(
    val humidity: Int,
    val rain: Double,
    val speed: Double,
    val description: String,
    val icon: String,
    val main: String,
    val windSpeed: Double
)