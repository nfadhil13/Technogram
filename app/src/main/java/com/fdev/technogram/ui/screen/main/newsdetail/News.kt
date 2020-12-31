package com.fdev.technogram.ui.screen.main.newsdetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.fdev.technogram.model.News

@Composable
fun NewsDetail(news : News){
    Text("news title : ${news.title}")
}