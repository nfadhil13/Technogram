package com.fdev.technogram.ui.screen.main.newsdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fdev.technogram.model.News

class NewsDetailViewModel @ViewModelInject constructor(
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