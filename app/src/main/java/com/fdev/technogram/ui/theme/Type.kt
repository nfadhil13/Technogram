package com.fdev.technogram.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.fdev.technogram.R

val poppins = fontFamily(
        font(R.font.poppins , FontWeight.Normal),
        font(R.font.poppins_bold , FontWeight.Bold),
        font(R.font.poppins_semibold , FontWeight.SemiBold),
        font(R.font.poppins_medium , FontWeight.Medium),
        font(R.font.poppins_light , FontWeight.Light)
)

// Set of Material typography styles to start with
val typography = Typography(defaultFontFamily = poppins)