package com.myosetpaing.comfortweatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {

    @RequiresApi(Build.VERSION_CODES.O)
    fun StringToDate(date:String):String{

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = LocalDate.parse(date, dateFormatter)
        val res = DateTimeFormatter.ofPattern("MMMM dd").format(formattedDate)
        return res
    }

}