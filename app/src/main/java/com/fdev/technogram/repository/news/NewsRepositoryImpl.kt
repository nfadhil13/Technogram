package com.fdev.technogram.repository.news

import com.fdev.technogram.datasource.network.service.NewsApiService
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject
constructor(
    @Inject private val  newsApiService: NewsApiService
) : NewsRepository{


    override suspend fun getRecentNews(
            perpage: Int,
            searchKey: String,
            page: Int
    ) : Flow<Event<News>> = flow {
        val newtworkCall = newsApiService
        newtworkCal
    }


    override suspend fun getMostLikedNews(perpage: Int, searchKey: String, page: Int): Flow<Event<News>> {
        TODO("Not yet implemented")
    }


}