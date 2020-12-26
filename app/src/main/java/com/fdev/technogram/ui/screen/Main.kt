package com.fdev.technogram.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TechnogramMain(){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 5.dp
            ){

            }
        },
        bodyContent = {
            Text("This is body")
        },
        drawerContent = {
            Text("This is drawer")
        }

    )
}