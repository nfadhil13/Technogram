package com.fdev.technogram.ui.screen.home

import com.fdev.technogram.model.News
import com.fdev.technogram.repository.news.FethMostLikedNews

//Define all posible type in lazycolumn
sealed class HomeViewType{


    data class RecentNews(val news : News) : HomeViewType()

    data class TopOfHome(val headerNews: News , val mostLikedNews : List<News>) : HomeViewType()




}
