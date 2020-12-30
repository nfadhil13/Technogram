package com.fdev.technogram.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel @ViewModelInject constructor(
    private val newsInteractors: NewsInteractors
) : ViewModel() {

    var homeViewTypes: List<HomeViewType> by mutableStateOf(listOf())

    private val loading = HomeViewType.LoadingItem

    private var currentPage = 1

    private var isFetching = false

    init {
        fetchMostLikedNews()
        fetchCurrentNews()
    }

    private fun fetchMostLikedNews() {
        viewModelScope.launch(Main) {
            newsInteractors.fetchMostLikedNews.fetch(perpage = 6, dispatcher = IO)
                .collect { result ->
                    when (result) {
                        is DataState.OnSuccess -> {
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


    private fun fetchCurrentNews(page: Int = currentPage) {
        viewModelScope.launch(Main) {
            isFetching = true
            toogleLoading()
            newsInteractors.fetchRecentNews.fetch(perpage = 10, dispatcher = IO, page = page)
                .collect { result ->
                    toogleLoading()
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