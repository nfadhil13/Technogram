package com.fdev.technogram.ui.screen.main.newsdetail

import com.fdev.technogram.model.News

sealed class SearchViewType{

    object NoMoreItem : SearchViewType()

    object NoItemFound : SearchViewType()

    data class NewsItem(val news : News) : SearchViewType()

    object Loading : SearchViewType()

}