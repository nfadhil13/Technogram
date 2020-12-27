package com.fdev.technogram.ui.screen.home

import com.fdev.technogram.model.News

//Define all posible type in lazycolumn
sealed class HomeViewType{


    data class RecentNews(val news : News) : HomeViewType()

    object TopOfHome : HomeViewType()




}
