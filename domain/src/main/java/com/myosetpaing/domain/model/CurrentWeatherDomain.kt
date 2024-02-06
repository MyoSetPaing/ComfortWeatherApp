package com.myosetpaing.domain.model

data class CurrentWeatherDomain(
    val latitude: Double?,
    val longitude: Double?,
    val id: String?,
    val humidity: Int?,
    val temp: Double?,
    val name: String?,
    val country: String?,
    val timezone: String?,
    val description: String?,
    val icon: String?,
    val windSpeed: Double?,
    val pressure:Double?
)

