package com.myosetpaing.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myosetpaing.data.local.entity.CurrentWeatherEntity
import com.myosetpaing.data.local.entity.WeatherForecastEntity
import com.myosetpaing.domain.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    @Query("SELECT * FROM CurrentWeatherEntity")
    fun getCurrentWeatherLocal(): Flow<CurrentWeatherEntity>

    @Query("SELECT * FROM WeatherForecastEntity")
    fun getWeatherForecastLocal(): Flow<WeatherForecastEntity>



}