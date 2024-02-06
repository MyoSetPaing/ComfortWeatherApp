package com.myosetpaing.data.di

import android.app.Application
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.myosetpaing.data.BuildConfig
import com.myosetpaing.data.Constants.BASE_URL
import com.myosetpaing.data.internet.InternetConnectionCheckerImpl
import com.myosetpaing.data.local.ComfortWeatherDatabase
import com.myosetpaing.data.local.typeconvertor.ForecastDataTypeConvertor
import com.myosetpaing.data.remote.ComfortWeatherApi
import com.myosetpaing.domain.internet.InternetConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BASIC
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .addNetworkInterceptor(logger)
            .addInterceptor { interceptor ->
                val requestBuilder = interceptor.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")

                val request = requestBuilder
                    .build()
                interceptor.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInstance(okHttpClient: OkHttpClient): ComfortWeatherApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ComfortWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(
        application: Application
    ): ComfortWeatherDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ComfortWeatherDatabase::class.java,
            name = "weather_db"
        ).addTypeConverter(ForecastDataTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideInternetConnectionChecker(
        application: Application
    ): InternetConnectionChecker = InternetConnectionCheckerImpl(application)

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}