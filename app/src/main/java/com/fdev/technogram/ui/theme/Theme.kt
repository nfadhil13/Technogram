package com.fdev.technogram.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fdev.technogram.ui.shapes
import com.fdev.technogram.ui.technogramRed400
import com.fdev.technogram.ui.technogramRed600


val DarkColorPalette = darkColors(
        primary = technogramRed600,
        primaryVariant = technogramRed400,
        secondary = technogramRed600,
)

val LightColorPalette = lightColors(
        primary = technogramRed600,
        primaryVariant = technogramRed400,
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
            shapes = shapes,
            content = content
    )
}