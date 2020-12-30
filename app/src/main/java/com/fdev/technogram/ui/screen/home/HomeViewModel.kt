package com.fdev.technogram.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(
    private val newsInteractors: NewsInteractors
) : ViewModel (){

    var homeViewTypes : List<HomeViewType> by mutableStateOf(listOf())

    private var currentPage = 1

    init{

        fetchMostLikedNews()
        fetchCurrentNews(1)
    }

    private fun fetchMostLikedNews(){
        viewModelScope.launch(Main){
            newsInteractors.fetchMostLikedNews.fetch(perpage = 6 , dispatcher = IO).collect { result ->
                when(result){
                    is DataState.OnSuccess -> {
                        addHomeViewType(0 , HomeViewType.TopOfHome(
                                headerNews = result.data[0],
                                mostLikedNews = result.data
                        ))
                    }

                    is DataState.OnFailure -> {
                            println("Error")
                    }
                }
            }
        }
    }


    private fun fetchCurrentNews(page : Int = 1){
        viewModelScope.launch(Main){
            newsInteractors.fetchRecentNews.fetch(perpage = 10 , dispatcher = IO , page = page).collect { result ->
                when(result){
                    is DataState.OnSuccess -> {
                        result.data.forEach { recentNews ->
                            addHomeViewType(
                                    homeViewType = HomeViewType.RecentNews(news = recentNews)
                            )
                        }
                        currentPage ++
                    }

                    is DataState.OnFailure -> {
                        println("Error")
                    }
                }
            }
        }
    }


    private fun addHomeViewType(index : Int = -1 , homeViewType: HomeViewType){
        homeViewTypes = homeViewTypes.toMutableList().also {
            if(index == -1){
                it.add(homeViewType)
            }else{
                it.add(index , homeViewType)
            }

        }
    }


    fun fetchCurrentNewsNextPage(){
        fetchCurrentNews(currentPage)
    }




}