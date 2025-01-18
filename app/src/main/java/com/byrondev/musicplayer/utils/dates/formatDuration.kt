package com.byrondev.musicplayer.utils.dates

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