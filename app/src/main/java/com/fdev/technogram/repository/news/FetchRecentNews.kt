package com.fdev.technogram.repository.news

import com.fdev.technogram.datasource.network.NetworkConst
import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.util.errorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import java.lang.Exception
import javax.inject.Inject


class FetchRecentNews
@Inject
constructor(
        private val newsNetworkDataSource: NewsNetworkDataSource
){

    companion object{
        const val FETCH_RECENT_NEWS_SUCCESS = "Success get recent news"
    }

    fun fetch(
            perpage: Int = 10, searchKey: String = "", page: Int = 1,
            dispatcher: CoroutineDispatcher
    ) : Flow<DataState<List<News>>> = flow {
        try{
            val result = withTimeout(NetworkConst.NETWORK_TIMEOUT ){
                newsNetworkDataSource.getRecentNews(perpage = perpage , searchKey = searchKey , page = page)
            }
            emit(DataState.OnSuccess(result , FETCH_RECENT_NEWS_SUCCESS))
        }catch(exception : Exception){
            val errorMessage = errorHandler(exception = exception)
            println(errorMessage)
            emit(DataState.OnFailure<List<News>>(errorMessage))
        }
    }.flowOn(dispatcher)
}