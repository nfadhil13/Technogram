package com.fdev.technogram.datasource.network.framework.service

import com.fdev.technogram.datasource.network.framework.model.ApiResponse
import com.fdev.technogram.datasource.network.framework.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {


    @GET("news/recent")
    suspend fun getRecentNews(
            @Query("perpage") perpage : Int = 10,
            @Query("key") searchKey : String = "",
            @Query("page") page : Int = 1
    ) : ApiResponse<List<NewsDto>>

    @GET("news/most-liked")
    suspend fun getMostLikedNews(
            @Query("perpage") perpage : Int = 10,
            @Query("key") searchKey : String = "",
            @Query("page") page : Int = 1
    ) : ApiResponse<List<NewsDto>>

}