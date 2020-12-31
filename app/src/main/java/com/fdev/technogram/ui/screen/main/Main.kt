package com.fdev.technogram.ui.screen.main

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.main.home.Home

@Composable
fun TechnogramMain(
){
    Scaffold(
        topBar = {
            TechnogramTopAppBar()
        },
        bodyContent = {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = MainScreen.HomeScreen.route,
                builder = {

                    //Home
                    composable(
                        route = MainScreen.HomeScreen.route
                    ) {
                        Home(onNewsClicked = { /*TODO*/ })
                    }


                    //News Detail
                    composable(
                        route = MainScreen.NewsDetailScreen.route
                    ){

                    }
                })
        },
        drawerContent = {
            Text("This is drawer")
        },

        )
}

sealed class MainScreen(val route : String){
    object HomeScreen : MainScreen(route = "profile")
    object NewsDetailScreen : MainScreen(route = "news_detail")
}