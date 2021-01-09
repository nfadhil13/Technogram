package com.fdev.technogram.datasource.network.framework.service

import com.fdev.technogram.datasource.network.framework.model.ApiResponse
import com.fdev.technogram.datasource.network.framework.model.CategoryDto
import com.fdev.technogram.datasource.network.framework.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApiService {

    @GET("kategori/get")
    suspend fun getRecentNews() : ApiResponse<List<CategoryDto>>


}