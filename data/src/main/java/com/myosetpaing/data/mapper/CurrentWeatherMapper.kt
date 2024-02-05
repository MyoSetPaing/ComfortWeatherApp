package com.myosetpaing.data.mapper

import com.myosetpaing.data.local.entity.CurrentWeatherEntity
import com.myosetpaing.data.remote.dto.CurrentWeatherDto
import com.myosetpaing.domain.model.CurrentWeatherDomain

fun CurrentWeatherDto.toCurrentWeatherEntity(): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        latitude = coord.lat,
        longitude = coord.lon,
        country = sys.country,
        description = weather[0].description,
        humidity = main.humidity,
        icon = weather[0].icon,
        id = id,
        main = weather[0].main,
        name = name,
        temp = main.temp,
        tempMax = main.temp_max,
        tempMin = main.temp_min,
        timezone = timezone,
        visibility = visibility,
        windSpeed = wind.speed
    )
}

fun CurrentWeatherEntity.toCurrentWeatherDomain(): CurrentWeatherDomain {
    return CurrentWeatherDomain(
        latitude = latitude,
        longitude = longitude,
        country = country,
        description = description,
        humidity = humidity,
        icon = icon,
        id = id,
        main = main,
        name = name,
        temp = temp,
        tempMax = tempMax,
        tempMin = tempMin,
        timezone = timezone,
        visibility = visibility,
        windSpeed = windSpeed
    )
}