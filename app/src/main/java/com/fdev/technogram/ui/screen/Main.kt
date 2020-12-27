package com.fdev.technogram.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.home.Home

@Composable
fun TechnogramMain(){
    Scaffold(
            topBar = {
                TechnogramTopAppBar()
            },
            bodyContent = {
                Home(onNewsClicked = { })
            },
            drawerContent = {
                Text("This is drawer")
            }

    )
}