package com.byrondev.musicplayer.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.random.Random


fun randomColor() : Color {

    val  red = Random.nextInt(0, 256)
    val green = Random.nextInt(0, 256)
    val blue = Random.nextInt(0, 256)

    return  Color(red, green, blue)

}

fun randomColorBrush() : Brush {
        val color1 = randomColor()
        val color2 = randomColor()
        return  Brush.linearGradient(
            colors = listOf(color1, color2)
        )

}

