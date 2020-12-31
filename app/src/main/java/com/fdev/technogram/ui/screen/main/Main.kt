package com.fdev.technogram.ui.screen.main

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.main.home.Home
import com.fdev.technogram.ui.screen.main.newsdetail.NewsDetail
import com.github.zsoltk.compose.router.Router

@Composable
fun TechnogramMain(
){

    val mainViewModel : MainViewModel = viewModel()
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
            MainContent(mainViewModel = mainViewModel)
        },
        drawerContent = {
            Text("This is drawer")
        },

        )
}


@Composable
fun MainContent(mainViewModel: MainViewModel){
    Router(defaultRouting = (MainScreen.HomeScreen as MainScreen)) { backStack ->
        when(backStack.last()){
            is MainScreen.HomeScreen -> {
                Home(onNewsClicked = { clickedNews ->
                    mainViewModel.currentNews =  clickedNews
                    backStack.push(MainScreen.NewsDetailScreen)
                })
            }
            is MainScreen.NewsDetailScreen -> {
                NewsDetail(news = mainViewModel.currentNews)
            }
        }
    }
}



sealed class MainScreen(){
    object HomeScreen : MainScreen()
    object NewsDetailScreen : MainScreen()
}