package ru.igorcodes.livescoreapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.igorcodes.livescoreapp.R

object Nunito {
    val Light = Font(R.font.nunito_light, FontWeight.W300)
    val Regular = Font(R.font.nunito_regular, FontWeight.W400)
    val Medium = Font(R.font.nunito_medium, FontWeight.W500)
    val Bold = Font(R.font.nunito_bold, FontWeight.W600)
}

private val NunitoFontFamily =
    FontFamily(listOf(Nunito.Light, Nunito.Regular, Nunito.Medium, Nunito.Bold))

val LiveScoreAppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        color = Color.White
    ),
    bodySmall = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)