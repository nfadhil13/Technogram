package com.fdev.technogram.ui.screen.main

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.navigate
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.main.home.Home
import com.fdev.technogram.ui.screen.main.newsdetail.NewsDetail

@Composable
fun TechnogramMain(
){

    val mainViewModel : MainViewModel = viewModel()
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TechnogramTopAppBar()
        },
//        bodyContent = {
//            Home(
//                onNewsClicked = {}
//            )
//        },
        bodyContent = {
            NavHost(
                navController = navController,
                startDestination = MainScreen.HomeScreen.route,
                builder = {

                    //Home
                    composable(
                        route = MainScreen.HomeScreen.route
                    ) {
                        Home(onNewsClicked = { news ->
                            mainViewModel.currentNews = news
                            navController.navigate("${MainScreen.NewsDetailScreen.route}/${news.id}")
                        })
                    }
                    //News Detail
                    composable(
                        route = MainScreen.NewsDetailScreen.route,
                        arguments = listOf(
                            navArgument(name = MainScreen.NewsDetailScreen.argument0) {
                                type = NavType.IntType
                            }
                        )
                    ) {

                        NewsDetail(news = mainViewModel.currentNews)
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
    object NewsDetailScreen : MainScreen(route = "news-detail"){
        const val routeWithArgument : String = "news-detail/{newsId}"
        const val argument0 : String = "newsId"
    }
}