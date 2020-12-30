package com.fdev.technogram.datasource.network.business.abstraction

import com.fdev.technogram.model.News


interface NewsNetworkDataSource{

    suspend fun getRecentNews(
            perpage : Int = 10 ,
            searchKey : String = "",
            page : Int = 1
    ) : List<News>


    suspend fun getMostLikedNews(
            perpage : Int = 10 ,
            searchKey : String = "",
            page : Int = 1
    ) : List<News>

}