package com.myosetpaing.data.local.typeconvertor

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.myosetpaing.data.local.entity.ForecastDataList

@ProvidedTypeConverter
class ForecastDataTypeConvertor {

    @TypeConverter
    fun convertForecastDataListToJSONString(forecastDataEntity: ForecastDataList): String =
        Gson().toJson(forecastDataEntity)

    @TypeConverter
    fun convertJSONStringToForecastDataList(jsonString: String): ForecastDataList =
        Gson().fromJson(jsonString, ForecastDataList::class.java)

}