package com.byrondev.musicplayer.utils.dates

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun parseYear(yearString : String) :Year?  {

    val formatter = DateTimeFormatter.ofPattern("yyyy")
    return try {
        Year.parse(yearString, formatter)
    } catch (e: DateTimeParseException) {
        println("Error parsing year: ${e.message}")
        null
    }

}


@RequiresApi(Build.VERSION_CODES.O)
fun parseDate(date : String) :String? {
    val removeSuffixDate = date.substringBefore("T")
    try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-M-d")

        val newDate = LocalDate.parse(removeSuffixDate, formatter)
        val monthText = newDate.format(DateTimeFormatter.ofPattern("MMMM", Locale("es", "ES")))

        return  "${newDate.dayOfMonth} de $monthText de ${newDate.year}"
    }
    catch (error : Throwable) {
        Log.e("Error formating", error.message.toString())

        return null
    }
}