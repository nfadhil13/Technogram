package com.fdev.technogram.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



val DarkColorPalette = darkColors(

)

val LightColorPalette = lightColors(
        primary = TECHNOGRAM_RED,
        primaryVariant = Color(255, 102, 99),
        surface = Color.White,
        background = Color.White,
        error = Color(176, 0, 32),
        onPrimary = Color.White,
        onBackground = Color.Black,
        onSurface = Color.Black,
        onError = Color.White
)

@Composable
fun TechnogramTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}