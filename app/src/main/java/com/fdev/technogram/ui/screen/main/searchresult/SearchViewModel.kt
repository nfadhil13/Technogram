package com.fdev.technogram.ui.screen.main.searchresult

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import com.fdev.technogram.ui.screen.main.searchresult.SearchMethod.MOST_LIKED
import com.fdev.technogram.ui.screen.main.searchresult.SearchMethod.RECENT_NEWS
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel
@ViewModelInject
constructor(
        private val newsInteractors: NewsInteractors
) : ViewModel(){


    var searchQuery : String by mutableStateOf("")
        private set


    var searchMethod : SearchMethod by mutableStateOf(RECENT_NEWS)
        private set


    var news : List<News> by mutableStateOf(listOf())

    private var lastSearchQuery : String? = null

    private var currentPage = -1

    private var searchJob : Job = Job()



    fun search(){
        if(currentPage == -1 ) return ;
        if(searchJob.isActive) searchJob.cancel()
        if(isNewSearch()){
            lastSearchQuery = searchQuery
            currentPage = 1
        }
        val fetchMethod = if(searchMethod ==  MOST_LIKED)
            newsInteractors.fetchMostLikedNews.fetch(10 , searchQuery ,currentPage ,IO)
        else
            newsInteractors.fetchRecentNews.fetch(10 , searchQuery ,currentPage ,IO)
        fetchNews(fetch = fetchMethod)
    }

    private fun fetchNews(fetch: Flow<DataState<List<News>>>) {
        searchJob = viewModelScope.launch(Main){
            fetch.collect { result ->
                when(result){
                    is DataState.OnSuccess -> {
                        if(result.data.isNotEmpty()){
                            if(currentPage == 1){
                                changeNews(result.data)
                            }else{
                                addNews(result.data)
                            }
                            currentPage ++
                        }else{
                            if(currentPage == 1){
                                // Tell that if its empty and no news with such query
                            }else{
                               // Tell that there is no more item to fetch
                            }
                            currentPage = -1
                        }
                    }

                    is DataState.OnFailure -> {
                        //TODO something when error
                    }
                }
            }
        }
/*        searchJob.start()*/
    }

    private fun changeNews(data: List<News>) {
        news = data
    }

    private fun addNews(data : List<News>){
        news = news.toMutableList().also{
            it.addAll(data)
        }
    }


    fun changeSearchQuery(newSearchQuery : String){
        searchQuery = newSearchQuery
    }

    private fun isNewSearch() : Boolean{
        return searchQuery == lastSearchQuery
    }

}