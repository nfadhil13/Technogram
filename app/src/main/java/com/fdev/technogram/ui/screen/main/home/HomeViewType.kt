package com.fdev.technogram.ui.screen.main.home

import com.fdev.technogram.model.News

//Define all posible type in lazycolumn
sealed class HomeViewType{


    data class RecentNews(val news : News) : HomeViewType()

    data class TopOfHome(val headerNews: News , val mostLikedNews : List<News>) : HomeViewType()

    object Skeleton : HomeViewType()

    object NoMoreItem : HomeViewType()

    object LoadingItem : HomeViewType()


    //Will be placed in the top of listview to enable swiperefreshlayout
    object EmptypSpace : HomeViewType()
}
