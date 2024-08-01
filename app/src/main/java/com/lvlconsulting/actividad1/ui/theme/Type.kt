package com.lvlconsulting.actividad1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lvlconsulting.actividad1.R


val Geologica = FontFamily(
    Font(R.font.geologica_regular, FontWeight.Normal),
    Font(R.font.geologica_medium, FontWeight.Medium),
    Font(R.font.geologica_bold, FontWeight.Bold),
    Font(R.font.geologica_black, FontWeight.Black),
    Font(R.font.geologica_extrabold, FontWeight.ExtraBold),
    Font(R.font.geologica_light, FontWeight.Light),
    Font(R.font.geologica_semibold, FontWeight.SemiBold),
    Font(R.font.geologica_thin, FontWeight.Thin)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Geologica,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)