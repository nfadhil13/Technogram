package com.fdev.technogram.repository.news

import com.fdev.technogram.model.News
import com.fdev.technogram.repository.Event
import kotlinx.coroutines.flow.Flow


interface NewsRepository {


    suspend fun getRecentNews(
            perpage : Int = 10 ,
            searchKey : String = "",
            page : Int = 1
    ) : Flow<Event<News>>

    suspend fun getMostLikedNews(
            perpage : Int = 10,
           searchKey : String = "",
             page : Int = 1
    ): Flow<Event<News>>

}