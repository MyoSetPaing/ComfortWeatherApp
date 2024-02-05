package com.myosetpaing.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myosetpaing.data.local.dao.CurrentWeatherDao
import com.myosetpaing.data.local.entity.CurrentWeatherEntity
import com.myosetpaing.data.local.entity.WeatherForecastEntity
import com.myosetpaing.data.local.typeconvertor.ForecastDataTypeConvertor

@TypeConverters(value = [ForecastDataTypeConvertor::class])
@Database(entities = [CurrentWeatherEntity::class, WeatherForecastEntity::class], version = 1)
abstract class ComfortWeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): CurrentWeatherDao
}
