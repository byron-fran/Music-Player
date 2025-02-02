package com.byrondev.musicplayer.utils.dates

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Year
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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