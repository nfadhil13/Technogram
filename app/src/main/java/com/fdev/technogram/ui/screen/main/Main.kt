package com.fdev.technogram.ui.screen.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.main.home.Home
import com.fdev.technogram.ui.screen.main.newsdetail.NewsDetail
import com.fdev.technogram.util.produceFakeNewsData
import com.github.zsoltk.compose.router.BackStack
import com.github.zsoltk.compose.router.Router

@Composable
fun TechnogramMain(
) {

    val mainViewModel: MainViewModel = viewModel()


    val scaffoldState = rememberScaffoldState()

    Router(defaultRouting = (MainScreen.HomeScreen as MainScreen)) { backStack ->
        Scaffold(
                topBar = {
                    TechnogramTopAppBar(
                            onBurgerClicked = {
                                scaffoldState.drawerState.open {

                                }
                            }
                    )
                },
//        bodyContent = {
//            Home(
//                onNewsClicked = {}
//            )
//        },
                bodyContent = {
                    MainContent(mainViewModel = mainViewModel, backStack = backStack)
                },
                drawerContent = {
                    Button(onClick = {
                        scaffoldState.drawerState.close {
                            backStack.push(MainScreen.HomeScreen)
                        }
                    }) {
                        Text("go home")
                    }
                    Button(onClick = {
                        scaffoldState.drawerState.close {
                            backStack.push(MainScreen.NewsDetailScreen(produceFakeNewsData()))
                        }
                    }) {
                        Text("go to fake news")
                    }
                },
                scaffoldState = scaffoldState
        )
    }

}


@Composable
fun MainContent(mainViewModel: MainViewModel, backStack: BackStack<MainScreen>) {
    when (val lastStack = backStack.last()) {
        is MainScreen.HomeScreen -> {
            Home(onNewsClicked = { clickedNews ->
                mainViewModel.currentNews = clickedNews
                backStack.push(MainScreen.NewsDetailScreen(clickedNews))
            })
        }
        is MainScreen.NewsDetailScreen -> {
            NewsDetail(news = lastStack.news)
        }
    }
}


sealed class MainScreen() {
    object HomeScreen : MainScreen()
    data class NewsDetailScreen(val news : News) : MainScreen()
}