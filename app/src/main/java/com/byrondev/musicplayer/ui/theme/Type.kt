package com.byrondev.musicplayer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.byrondev.musicplayer.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,

    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val textWhite15 = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,

    )

val textDarkGray13 = TextStyle (

    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
)

val textSmall  = TextStyle (
    fontSize = 10.sp,
    fontFamily = FontFamily(
        Font(resId = R.font.montserrat_regular)
    )

)
val textExtraSmall = TextStyle(
    fontSize = 12.sp,
    fontFamily = FontFamily(
        Font(resId = R.font.montserrat_medium)
    )
)
val textMedium = TextStyle(
    fontSize = 15.sp,
    fontFamily = FontFamily(
        Font(resId = R.font.montserrat_medium)
    )
)
val textLarge = TextStyle (
    fontSize = 18.sp,
    fontFamily = FontFamily(
        Font(resId = R.font.montserrat_bold)
    )
)
