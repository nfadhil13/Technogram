package com.fdev.technogram.ui.screen.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


const val HOME_SCROLL_STATE = "com.fdev.technogram.homescrollstate"

const val HOME_SCROLL_OFFSET = "com.fdev.technogram.homeoffsetstate"

const val HOME_LAST_PAGE = "com.fdev.technogram.homelastpage"

class HomeViewModel @ViewModelInject constructor(
        private val newsInteractors: NewsInteractors,
        @Assisted private val  savedStateHandle: SavedStateHandle
) : ViewModel() {


    companion object{
        private const val DEFAULT_PERPAGE = 6
    }

    var homeViewTypes: List<HomeViewType> by mutableStateOf(listOf())

    private val loading = HomeViewType.LoadingItem

    private var currentPage : Int = 1

    private var isFetching = false





    var scrollState = 0
        set(value){
            field = value
            savedStateHandle.set(HOME_SCROLL_STATE, value)
        }

    var scrollOffset = 0
        set(value){
            field = value
            savedStateHandle.set(HOME_SCROLL_OFFSET , value)
        }

    init {
        addHomeViewType(0, HomeViewType.Skeleton)
        fetchMostLikedNews()
        val lastPage = savedStateHandle.get<Int>(HOME_LAST_PAGE)
        if(lastPage == null){
            fetchCurrentNews()
        }else{
            fetchCurrentNews(page = 1 , perpage = lastPage * DEFAULT_PERPAGE)
            currentPage = lastPage
        }


    }

    private fun fetchMostLikedNews() {
        viewModelScope.launch(Main) {
            newsInteractors.fetchMostLikedNews.fetch(perpage = 6, dispatcher = IO)
                    .collect { result ->
                        when (result) {
                            is DataState.OnSuccess -> {
                                deleteHomeViewType(0)
                                addHomeViewType(
                                        0, HomeViewType.TopOfHome(
                                        headerNews = result.data[0],
                                        mostLikedNews = result.data
                                )
                                )
                            }

                            is DataState.OnFailure -> {
                                handleError(result.message)
                            }
                        }
                    }
        }
    }



    private fun fetchCurrentNews(page: Int = currentPage , perpage : Int = DEFAULT_PERPAGE) {
        viewModelScope.launch(Main) {
            isFetching = true
            if (page != 1) toogleLoading()
            newsInteractors.fetchRecentNews.fetch(perpage = perpage, dispatcher = IO, page = page)
                    .collect { result ->
                        if (page != 1) toogleLoading()
                        when (result) {
                            is DataState.OnSuccess -> {
                                if (result.data.isEmpty()) {
                                    currentPage = -1
                                    addHomeViewType(homeViewType = HomeViewType.NoMoreItem)
                                } else {
                                    addHomeViewTypes(
                                            homeViewType = result.data.map { HomeViewType.RecentNews(news = it) }
                                    )
                                    currentPage++
                                    savedStateHandle.set(HOME_LAST_PAGE , currentPage)
                                }
                            }

                            is DataState.OnFailure -> {
                                handleError(result.message)
                            }
                        }
                        isFetching = false
                    }
        }
    }

    private fun toogleLoading() {
        if (homeViewTypes.contains(loading)) {
            deleteHomeViewType(loading)
        } else {
            addHomeViewType(
                    index = homeViewTypes.size,
                    homeViewType = loading
            )
        }
    }


    private fun deleteHomeViewType(homeViewType: HomeViewType) {
        homeViewTypes = homeViewTypes.toMutableList().also {
            it.remove(homeViewType)
        }
    }

    private fun deleteHomeViewType(index: Int) {
        homeViewTypes = homeViewTypes.toMutableList().also {
            it.removeAt(index)
        }
    }


    private fun addHomeViewType(index: Int = -1, homeViewType: HomeViewType) {
        homeViewTypes = homeViewTypes.toMutableList().also {
            if (index == -1) {
                it.add(homeViewType)
            } else {
                it.add(index, homeViewType)
            }

        }
    }

    private fun addHomeViewTypes(index: Int = -1, homeViewType: List<HomeViewType>) {
        homeViewTypes = homeViewTypes.toMutableList().also {
            if (index == -1) {
                it.addAll(homeViewType)
            } else {
                it.addAll(index, homeViewType)

            }

        }
    }


    fun fetchCurrentNewsNextPage() {
        println("Fetchin for page : $currentPage")
        if (currentPage != -1 && !isFetching) {
            fetchCurrentNews(currentPage)
        }
    }


    private fun handleError(message: String) {
        println(message)
    }

    fun shouldFetchMore(index: Int): Boolean =
            (!isFetching && index > 0 && index == homeViewTypes.size - 1 && currentPage != -1)


}