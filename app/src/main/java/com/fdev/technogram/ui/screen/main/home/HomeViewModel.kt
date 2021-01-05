package com.fdev.technogram.ui.screen.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.news.NewsInteractors
import com.fdev.technogram.util.LazyListState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



/*
    This is viewmodel for home screen
 */
class HomeViewModel @ViewModelInject constructor(
        private val newsInteractors: NewsInteractors,
        @Assisted private val  savedStateHandle: SavedStateHandle
) : ViewModel() {

    //Define default fetch perpage for recent news
    companion object{
        private const val DEFAULT_PERPAGE = 6
    }

    var homeViewTypes: List<HomeViewType> by mutableStateOf(listOf())

    private val loading = HomeViewType.LoadingItem

    private var currentPage : Int = 1

    private var isFetching = false

    private val _isOnRefresh = MutableLiveData<Boolean>(false)

    val isOnRefresh: LiveData<Boolean>
        get() = _isOnRefresh



     var scrollState = LazyListState(index = 0, offset = 0)
    private  set

    init {
        init()
    }

    private fun init(){
        addHomeViewType(index = 0 , HomeViewType.Skeleton)
        viewModelScope.launch(Main){
            async{
                fetchMostLikedNews()
            }
            async {
                fetchCurrentNews()
            }
        }
    }

    fun refresh(){
        println("ON REFRESHH")
        homeViewTypes = listOf()
        _isOnRefresh.value = true
        addHomeViewType(index = 0 , HomeViewType.Skeleton)
        viewModelScope.launch(Main){
            val mostLikedNews =  async{ fetchMostLikedNews() }
            val recentNews = async{fetchCurrentNews()}
            mostLikedNews.await()
            recentNews.await()
            _isOnRefresh.value = false
        }
    }


    private suspend fun fetchMostLikedNews() {
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



    private suspend fun fetchCurrentNews(page: Int = currentPage , perpage : Int = DEFAULT_PERPAGE) {
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
                                }
                            }

                            is DataState.OnFailure -> {
                                handleError(result.message)
                            }
                        }
                        isFetching = false
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

    fun setScollState(index: Int = 0 , offset : Int = 0){
        scrollState = LazyListState(index = index , offset = offset)
    }

    fun fetchCurrentNewsNextPage() {
        println("Fetchin for page : $currentPage")
        if (currentPage != -1 && !isFetching) {
            viewModelScope.launch {
                fetchCurrentNews(currentPage)
            }
        }
    }

    private fun handleError(message: String) {
        println(message)
    }

    fun shouldFetchMore(index: Int): Boolean =
            (!isFetching && index > 0 && index == homeViewTypes.size - 1 && currentPage != -1)


}