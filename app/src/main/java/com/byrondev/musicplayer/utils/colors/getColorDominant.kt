package com.byrondev.musicplayer.utils.colors


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.palette.graphics.Palette

fun isPureBlack(color: Color): Boolean = color == Color.Black
fun isPureWhite(color: Color): Boolean = color == Color.White

fun isTooDark(color: Color): Boolean {

    return color.luminance() < 0.1f
}

fun isTooLight(color: Color): Boolean {
    return color.luminance() > 0.5f
}

fun chooseColorFromPalette(palette: Palette?): Color {
    if (palette == null) return Color.Black

    val swatches = listOfNotNull(
        palette.dominantSwatch,
        palette.vibrantSwatch,
        palette.lightVibrantSwatch,
        palette.darkVibrantSwatch,
        palette.mutedSwatch,
        palette.lightMutedSwatch,
        palette.darkMutedSwatch
    )

    val acceptableSwatches = swatches.filter { swatch ->
        val color = Color(swatch.rgb)
        !isPureBlack(color) && !isPureWhite(color) &&
                !isTooDark(color) && !isTooLight(color)
    }


    val chosenSwatch = if (acceptableSwatches.isNotEmpty()) {
        acceptableSwatches.maxByOrNull { it.population }
    } else {

        swatches.filter { swatch ->
            val color = Color(swatch.rgb)
            !isPureBlack(color) && !isPureWhite(color)
        }.maxByOrNull { it.population } ?: palette.dominantSwatch
    }


    return if (chosenSwatch != null) {
        Color(chosenSwatch.rgb)
    } else {
        Color.Black
    }
}

fun listColors(bestColor: Color): List<Color> {

    return listOf(
        bestColor.copy(alpha = 0.8f),
        bestColor.copy(alpha = 0.7f),
        bestColor.copy(alpha = 0.6f),
        bestColor.copy(alpha = 0.5f),
        bestColor.copy(alpha = 0.4f),
        bestColor.copy(alpha = 0.3f),
        bestColor.copy(alpha = 0.2f),
        bestColor.copy(alpha = 0.1f),
        Color.Black,
    )
}