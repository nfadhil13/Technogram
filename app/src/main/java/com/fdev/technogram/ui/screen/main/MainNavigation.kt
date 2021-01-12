package com.fdev.technogram.ui.screen.main

import androidx.annotation.DrawableRes

sealed class MainNavigation {

    object Home : MainNavigation()
    object Search : MainNavigation()
    data class SearchWithQuery(val query : String, @DrawableRes val icon : Int ) : MainNavigation()
    object More : MainNavigation()
    data class ChangeTheme(val darkTheme : Boolean): MainNavigation()

}