package com.myosetpaing.data.mapper

import com.myosetpaing.data.local.entity.ForecastDataEntity
import com.myosetpaing.data.local.entity.ForecastDataList
import com.myosetpaing.data.local.entity.WeatherForecastEntity
import com.myosetpaing.data.remote.dto.ForecastData
import com.myosetpaing.data.remote.dto.ForecastWeatherDto
import com.myosetpaing.domain.model.ForecastDataDomain
import com.myosetpaing.domain.model.WeatherForecastDomain

fun ForecastWeatherDto.toWeatherForecastEntity(): WeatherForecastEntity {
    return WeatherForecastEntity(
        cnt = cnt,
        country = city.country,
        countryId = city.id,
        latitude = city.coord.lat,
        longitude = city.coord.lon,
        list = ForecastDataList(list.map {
            it.toForecastDataEntity()
        }),
        message = message,
        name = city.name,
        population = city.population,
        timezone = city.timezone


    )
}


fun WeatherForecastEntity.toWeatherForecastDomain(): WeatherForecastDomain {
    return WeatherForecastDomain(
        cnt = cnt,
        country = country,
        countryId = countryId,
        latitude = latitude,
        longitude = longitude,
        list = list.list.map { it.toForecastDataDomain() },
        message = message,
        name = name,
        population = population,
        timezone = timezone


    )
}

fun ForecastData.toForecastDataEntity(
): ForecastDataEntity {
    return ForecastDataEntity(
        humidity = humidity,
        rain = rain,
        speed = speed,
        description = weather[0].description,
        icon = weather[0].icon,
        main = weather[0].main,
        windSpeed = speed
    )
}

fun ForecastDataEntity.toForecastDataDomain(
): ForecastDataDomain {
    return ForecastDataDomain(
        humidity = humidity,
        rain = rain,
        speed = speed,
        description = description,
        icon = icon,
        main = main,
        windSpeed = speed
    )
}
