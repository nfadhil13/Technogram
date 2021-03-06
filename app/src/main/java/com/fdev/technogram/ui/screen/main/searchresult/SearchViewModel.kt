package com.fdev.technogram.ui.screen.main.searchresult

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import com.fdev.technogram.ui.screen.main.newsdetail.SearchViewType
import com.fdev.technogram.ui.screen.main.searchresult.SearchMethod.MOST_LIKED
import com.fdev.technogram.ui.screen.main.searchresult.SearchMethod.RECENT_NEWS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel
@Inject
constructor(
        private val newsInteractors: NewsInteractors
) : ViewModel() {


    companion object {
        private const val SEARCH_FETCH_PERPAGE = 10
    }

    var searchQuery: String by mutableStateOf("")
        private set


    private var searchMethod: SearchMethod by mutableStateOf(RECENT_NEWS)


    var searchViews: List<SearchViewType> by mutableStateOf(listOf())
        private set


    private var currentPage = -1

    private var fetchJob: Job = Job()

    private val loadingItem = SearchViewType.Loading



    fun search() {
        viewModelScope.launch(Main) {
            cancleJobIfActive()
            clearNews()
            addItem(loadingItem)
            currentPage = 1
            fetchNews()
        }
    }

    fun fetchMore() {
        viewModelScope.launch(IO) {
            if (!fetchJob.isActive && currentPage != -1) {
                addItem(loadingItem)
                fetchNews()
            }
        }
    }

    private fun fetchNews() {
        val fetch = if (searchMethod == MOST_LIKED)
            newsInteractors.fetchMostLikedNews.fetch(SEARCH_FETCH_PERPAGE, searchQuery, currentPage, IO)
        else
            newsInteractors.fetchRecentNews.fetch(SEARCH_FETCH_PERPAGE, searchQuery, currentPage, IO)

        fetchJob = viewModelScope.launch(IO) {
            fetch.collect { result ->
                deleteItem(loadingItem)
                when (result) {
                    is DataState.OnSuccess -> {
                        if (result.data.isNotEmpty()) {
                            result.data.forEach {
                                addItem(SearchViewType.NewsItem(it))
                            }
                            if(result.data.size < SEARCH_FETCH_PERPAGE){
                                currentPage = -1
                                addItem(SearchViewType.NoMoreItem)
                            }else{
                                currentPage++
                            }
                        } else {
                            if (currentPage == 1) {
                                addItem(SearchViewType.NoItemFound)
                            } else {
                                addItem(SearchViewType.NoMoreItem)
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

    private fun clearNews() {
        viewModelScope.launch(Main) {
            searchViews = listOf()
        }

    }

    private fun addItem(searchView: SearchViewType) {
        viewModelScope.launch(Main) {
            searchViews = searchViews.toMutableList().also {
                it.add(searchView)

            }
        }
    }

    private fun deleteItem(searchView: SearchViewType){
        viewModelScope.launch(Main) {
            searchViews = searchViews.toMutableList().also {
                it.remove(searchView)

            }
        }
    }


    fun changeSearchQuery(newSearchQuery: String) {
        searchQuery = newSearchQuery
    }

    private fun cancleJobIfActive() {
        if (fetchJob.isActive) fetchJob.cancel()
    }




}