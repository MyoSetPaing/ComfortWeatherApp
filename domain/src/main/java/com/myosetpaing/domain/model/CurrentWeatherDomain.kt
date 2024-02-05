package com.myosetpaing.domain.model

data class CurrentWeatherDomain(
    val latitude: Double,
    val longitude: Double,
    val id: Int,
    val humidity: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double,
    val name: String,
    val rain: Double? = 1.0,
    val country: String,
    val timezone: Int,
    val visibility: Int,
    val description: String,
    val icon: String,
    val main: String,
    val windSpeed: Double
)

