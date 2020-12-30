package com.fdev.technogram.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.home.Home
import com.fdev.technogram.ui.screen.home.HomeViewModel

@Composable
fun TechnogramMain(
){
    Scaffold(
            topBar = {
                TechnogramTopAppBar()
            },
            bodyContent = {
                Home(onNewsClicked = { })
            },
            drawerContent = {
                Text("This is drawer")
            },

    )
}