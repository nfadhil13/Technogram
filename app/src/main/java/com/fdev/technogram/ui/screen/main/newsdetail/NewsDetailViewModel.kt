package com.fdev.technogram.ui.screen.main.newsdetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.news.NewsInteractors

class NewsDetailViewModel @ViewModelInject constructor(
@Assisted
private val  savedStateHandle: SavedStateHandle
) : ViewModel(){


    var news : News? = null
        private set

    var scrollState : Float = 0f
        private set


    fun onScrollStateChange(state : Float = 0f){
        scrollState = state
        println("scroll state ganti $scrollState")
    }

    fun setCurrentNews(newNews : News){
        news = newNews
    }
}