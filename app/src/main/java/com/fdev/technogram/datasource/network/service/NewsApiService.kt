package com.fdev.technogram.datasource.network.service

import com.fdev.technogram.datasource.network.model.ApiResponse
import com.fdev.technogram.datasource.network.model.news.NewsNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {


    @GET("news/recent")
    suspend fun getRecentNews(
            @Query("perpage") perpage : Int = 10,
            @Query("key") searchKey : String = "",
            @Query("page") page : Int = 1
    ) : ApiResponse<List<NewsNetworkEntity>>

    @GET("news/most-liked")
    suspend fun getMostLikedNews(
            @Query("perpage") perpage : Int = 10,
            @Query("key") searchKey : String = "",
            @Query("page") page : Int = 1
    ) : ApiResponse<List<NewsNetworkEntity>>

}