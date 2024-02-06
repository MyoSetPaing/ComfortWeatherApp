package com.myosetpaing.data.mapper

import com.myosetpaing.data.local.entity.ForecastDataEntity
import com.myosetpaing.data.local.entity.ForecastDataList
import com.myosetpaing.data.local.entity.WeatherForecastEntity
import com.myosetpaing.data.remote.dto.ForecastWeatherDto
import com.myosetpaing.data.remote.dto.Forecastday
import com.myosetpaing.domain.model.ForecastDataDomain
import com.myosetpaing.domain.model.WeatherForecastDomain

fun ForecastWeatherDto.toWeatherForecastEntity(): WeatherForecastEntity {
    return WeatherForecastEntity(
        country = location.country,
        countryId = location.tz_id,
        latitude = location.lat,
        longitude = location.lon,
        list = ForecastDataList(forecast.forecastday.map { it.toForecastDataEntity() }),
        name = location.name,
        timezone = location.localtime
    )
}


fun WeatherForecastEntity.toWeatherForecastDomain(): WeatherForecastDomain {
    return WeatherForecastDomain(
        country = country,
        countryId = countryId,
        latitude = latitude,
        longitude = longitude,
        list = list.list.map { it.toForecastDataDomain() },
        name = name,
        timezone = timezone


    )
}

fun Forecastday.toForecastDataEntity(
): ForecastDataEntity {
    return ForecastDataEntity(
        humidity = day.avghumidity,
        date = date,
        maxtemp_c = day.maxtemp_c,
        wind_mph = day.maxwind_mph,
        icon = day.condition.icon

    )
}

fun ForecastDataEntity.toForecastDataDomain(
): ForecastDataDomain {
    return ForecastDataDomain(
        humidity = humidity,
        date = date,
        maxtemp_c = maxtemp_c,
        wind_mph = wind_mph,
        icon = icon
    )
}
