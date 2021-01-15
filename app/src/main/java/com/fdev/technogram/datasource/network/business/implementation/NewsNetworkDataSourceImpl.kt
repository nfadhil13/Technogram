package com.fdev.technogram.datasource.network.business.implementation

import com.fdev.technogram.datasource.network.NetworkErrorConst
import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
import com.fdev.technogram.datasource.network.framework.mapper.NewsNetworkMapper
import com.fdev.technogram.datasource.network.framework.service.NewsApiService
import com.fdev.technogram.model.News
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import kotlin.Exception

class NewsNetworkDataSourceImpl
@Inject
constructor(
    private val newsNetworkMapper: NewsNetworkMapper,
    private val newsApiService: NewsApiService
) : NewsNetworkDataSource {


    override suspend fun getRecentNews(perpage: Int, searchKey: String, page: Int): List<News> {
            val result = newsApiService.getRecentNews(perpage = perpage , searchKey = searchKey , page = page)
            result.data?.let { data ->
                val newsList = ArrayList<News>()
                data.forEach { newsDto ->
                    newsList.add(newsNetworkMapper.mapToDomain(newsDto))
                }
                return newsList
            } ?: throw Exception(NetworkErrorConst.DATA_NULL)

    }

    override suspend fun getMostLikedNews(perpage: Int, searchKey: String, page: Int): List<News> {
        val result = newsApiService.getMostLikedNews(perpage = perpage , searchKey = searchKey , page = page)
        result.data?.let { data ->
            val newsList = ArrayList<News>()
            data.forEach { newsDto ->
                newsList.add(newsNetworkMapper.mapToDomain(newsDto))
            }
            return newsList
        } ?: throw Exception(NetworkErrorConst.DATA_NULL)
    }

}