package com.fdev.technogram.ui.screen.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import com.fdev.technogram.util.LazyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/*
    This is viewmodel for home screen
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
        private val newsInteractors: NewsInteractors,
//        @Assisted private val  savedStateHandle: SavedStateHandle
) : ViewModel() {

    //Define default fetch perpage for recent news
    companion object {
        private const val DEFAULT_PERPAGE = 6
    }

    var homeViewTypes: List<HomeViewType> by mutableStateOf(listOf())
        private set


    var isRefreshing : Boolean by mutableStateOf(false)

    private val loading = HomeViewType.LoadingItem


    private val skeleton = HomeViewType.Skeleton

    private var isError = false


    private var currentPage: Int = 1


    var scrollState: LazyListState by mutableStateOf(LazyListState(index = 0, offset = 0))
        private set


    private var fetchJob: Job = Job()

    init {
        addHomeViewType(index = 0, HomeViewType.EmptypSpace)
        fetchNews()
    }

    private fun fetchNews() {
        fetchJob = viewModelScope.launch(Main) {
            addHomeViewType(index = 1, skeleton)
            async {
                isRefreshing = true
                fetchMostLikedNews()
                isRefreshing = false
                deleteHomeViewType(skeleton)
            }
            async {
                fetchCurrentNews()
            }

        }
    }


    fun refresh() {
        if (!fetchJob.isActive) {
            isError = false
            println("refresh")
            currentPage = 1
            clearAllExceptFirstIndex()
            fetchNews()
        }
    }

    fun clearAllExceptFirstIndex() {
        homeViewTypes = homeViewTypes.toMutableList().also { list ->
            list.removeAll { homeViewType ->
                homeViewType !is HomeViewType.EmptypSpace
            }
        }
    }


    private suspend fun fetchMostLikedNews() {
        newsInteractors.fetchMostLikedNews.fetch(perpage = 6, dispatcher = IO)
                .collect { result ->
                    when (result) {
                        is DataState.OnSuccess -> {
                            addHomeViewType(
                                    1, HomeViewType.TopOfHome(
                                    headerNews = result.data[0],
                                    mostLikedNews = result.data
                            )
                            )
                        }

                        is DataState.OnFailure -> {
                            isError = true
                            addHomeViewType(homeViewType = HomeViewType.Error(result.message))
                        }
                    }
                }
    }


    private suspend fun fetchCurrentNews(page: Int = currentPage, perpage: Int = DEFAULT_PERPAGE) {
        withContext(IO) {
            newsInteractors.fetchRecentNews.fetch(perpage = perpage, dispatcher = IO, page = page)
                    .collect { result ->
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
                                isError = true
                                addHomeViewType(homeViewType = HomeViewType.Error(result.message))
                            }
                        }
                    }
        }

    }

    private fun toogleLoading(isShowLoading: Boolean) {
        viewModelScope.launch(Main) {
            if (!isShowLoading) {
                if (homeViewTypes.contains(loading)) {
                    deleteHomeViewType(loading)
                }
            } else {
                addHomeViewType(homeViewType = loading)
            }
        }
    }


    private fun deleteHomeViewType(homeViewType: HomeViewType) {
        viewModelScope.launch(Main) {
            homeViewTypes = homeViewTypes.toMutableList().also {
                it.remove(homeViewType)
            }
        }

    }


    private fun addHomeViewType(index: Int = -1, homeViewType: HomeViewType) {
        viewModelScope.launch(Main) {
            homeViewTypes = homeViewTypes.toMutableList().also {
                if (index == -1) {
                    it.add(homeViewType)
                } else {
                    it.add(index, homeViewType)
                }

            }
        }

    }

    private fun addHomeViewTypes(index: Int = -1, homeViewType: List<HomeViewType>) {
        viewModelScope.launch(Main) {
            homeViewTypes = homeViewTypes.toMutableList().also {
                if (index == -1) {
                    it.addAll(homeViewType)
                } else {
                    it.addAll(index, homeViewType)

                }

            }
        }

    }

    fun setScollState(index: Int = 0, offset: Int = 0) {
        scrollState = LazyListState(index = index, offset = offset)
    }

    fun fetchCurrentNewsNextPage(listVieItemIndex: Int) {
        if (shouldFetchMore(listVieItemIndex)) {
            fetchJob = viewModelScope.launch {
                toogleLoading(true)
                fetchCurrentNews(currentPage)
                toogleLoading(false)
            }
        }
    }

    private fun handleError(message: String) {
        println(message)
    }

    private fun shouldFetchMore(listVieItemIndex: Int): Boolean =
            (!fetchJob.isActive && listVieItemIndex > 0 && listVieItemIndex == homeViewTypes.lastIndex && currentPage != -1 && !isError)



}