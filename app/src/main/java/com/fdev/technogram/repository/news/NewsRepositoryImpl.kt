package com.fdev.technogram.repository.news

import com.fdev.technogram.datasource.network.NetworkConst.BASE_URL
import com.fdev.technogram.datasource.network.service.NewsApiService
import com.fdev.technogram.model.News
import com.fdev.technogram.repository.ErrorConst
import com.fdev.technogram.repository.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.TemporalField
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NewsRepositoryImpl @Inject
constructor(
     private val  newsApiService: NewsApiService
) : NewsRepository{


    override suspend fun getRecentNews(
            perpage: Int,
            searchKey: String,
            page: Int
    ) : Flow<Event<List<News>>> = flow {
        try{
            val newtworkCall = newsApiService.getRecentNews(
                    perpage = perpage,
                    searchKey = searchKey,
                    page = page
            )
            newtworkCall.data?.let{ newsNetworkEntity ->
                val listOfNews = ArrayList<News>()
                for(news in newsNetworkEntity){
                    listOfNews.add(
                            News(
                                    id = news.id_berita,
                                    title = news.judul,
                                    headerImg = BASE_URL + news.url_gambar,
                                    article = news.artikel,
                                    likes = news.jumlah_likes,
                                    reads = news.jumlah_reader,
                                    writer = news.jurnalis,
                                    category = news.kategori_berita,
                                    publishTime = Date().time
                            )
                    )
                }
                emit(Event.OnSuccess<List<News>>(
                        data = listOfNews,
                        message = newtworkCall.message
                ))
            } ?: throw Exception(ErrorConst.NULL_DATA)


        }catch (exception : Exception){
            emit(Event.OnFailure<List<News>>(message = exception.message.toString()))
        }

    }


    //    override suspend fun getMostLikedNews(perpage: Int, searchKey: String, page: Int): Flow<Event<News>> {
    //        TODO("Not yet implemented")
    //    }


}