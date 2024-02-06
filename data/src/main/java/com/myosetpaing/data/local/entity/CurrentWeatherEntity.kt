package com.myosetpaing.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentWeatherEntity(
    val latitude: Double?,
    val longitude: Double?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val humidity: Int?,
    val temp: Double?,
    val name: String?,
    val country: String?= null,
    val timezone: String?,
    val description: String?,
    val icon: String?,
    val windSpeed: Double?,
    val pressure:Double?
)