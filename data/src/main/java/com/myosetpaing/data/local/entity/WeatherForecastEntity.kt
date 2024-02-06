package com.myosetpaing.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WeatherForecastEntity(
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val countryId: String,
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val timezone: String,
    val list: ForecastDataList,
)


data class ForecastDataEntity(
    val date: String,
    val humidity: Int,
    val wind_mph: Double,
    val maxtemp_c: Double,
    val icon:String

    )
data class ForecastDataList(
    val list: List<ForecastDataEntity>,
)