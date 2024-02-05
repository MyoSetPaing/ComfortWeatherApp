package com.myosetpaing.data.di


import com.myosetpaing.data.remote.repository.CurrentWeatherRepositoryImpl
import com.myosetpaing.data.remote.repository.WeatherForecastRepositoryImpl
import com.myosetpaing.domain.repository.CurrentWeatherRepository
import com.myosetpaing.domain.repository.WeatherForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCurrentWeatherRepository(currentWeatherRepositoryImpl: CurrentWeatherRepositoryImpl): CurrentWeatherRepository

    @Binds
    abstract fun provideWeatherForecastRepository(weatherForecastRepositoryImpl:WeatherForecastRepositoryImpl): WeatherForecastRepository


}