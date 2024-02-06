package com.myosetpaing.data.mapper

import com.myosetpaing.data.local.entity.CurrentWeatherEntity
import com.myosetpaing.data.remote.dto.CurrentWeatherDto
import com.myosetpaing.domain.model.CurrentWeatherDomain

fun CurrentWeatherDto.toCurrentWeatherEntity(): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        latitude = location.lat,
        longitude = location.lon,
        country = location.country,
        description = current.condition.text,
        humidity = current.humidity,
        icon = current.condition.icon,
        id = location.tz_id,
        name = location.name,
        temp = current.temp_c,
        timezone = location.localtime,
        windSpeed = current.wind_mph,
        pressure = current.pressure_in
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
        name = name,
        temp = temp,
        timezone = timezone,
        windSpeed = windSpeed,
        pressure = pressure
    )
}