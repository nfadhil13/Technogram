package com.fdev.technogram.ui.screen.main

import androidx.annotation.DrawableRes

sealed class MainNavigation {

    object Home : MainNavigation()
    data class Search(val query : String , @DrawableRes val icon : Int ) : MainNavigation()
    object More : MainNavigation()

}