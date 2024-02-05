package com.myosetpaing.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WeatherForecastEntity(
    val latitude: Double,
    val longitude: Double,
    val country: String,
    @PrimaryKey(autoGenerate = false)
    val countryId: Int,
    val name: String,
    val population: Int,
    val timezone: Int,
    val cnt: Int,
    val list: ForecastDataList,
    val message: Double
)


data class ForecastDataEntity(
    val humidity: Int,
    val rain: Double,
    val speed: Double,
    val description: String,
    val icon: String,
    val main: String,
    val windSpeed: Double
)
data class ForecastDataList(
    val list: List<ForecastDataEntity>,
)