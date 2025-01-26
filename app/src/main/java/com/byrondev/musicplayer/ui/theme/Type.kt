package com.byrondev.musicplayer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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
//    color = Gray30, Todo add color typography
    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
)

val textSmall  = TextStyle (
    fontSize = 10.sp,
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,

)
val textExtraSmall = TextStyle(
    fontSize = 13.sp,
    fontWeight = FontWeight.W500,
)
val textMedium = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.W500,
)
val textLarge = TextStyle (
    fontSize = 19.sp,
    fontWeight = FontWeight.Normal,
)