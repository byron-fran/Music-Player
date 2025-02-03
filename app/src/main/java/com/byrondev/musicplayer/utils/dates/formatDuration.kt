package com.byrondev.musicplayer.utils.dates

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration

fun formatDuration(milliseconds: Int): String {
    val seconds = milliseconds / 1000
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60

    return if (hours > 0) {
        String.format("%02d:%02d:%02d".lowercase(), hours, minutes, remainingSeconds)
    } else {
        String.format("%02d:%02d".lowercase(), minutes, remainingSeconds)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDurationText(millis: Long): String {

    val duration = Duration.ofMillis(millis)
    val days = duration.toDays()
    val hours = duration.toHours() % 24
    val minutes = duration.toMinutes() % 60

    return buildString {
        if (days > 0) append("$days día${if (days > 1) "s" else ""}, ")
        if (hours > 0) append("$hours hora${if (hours > 1) "s" else ""}, ")
        if (minutes > 0) append("$minutes minuto${if (minutes > 1) "s" else ""} ")
    }.trim()
}