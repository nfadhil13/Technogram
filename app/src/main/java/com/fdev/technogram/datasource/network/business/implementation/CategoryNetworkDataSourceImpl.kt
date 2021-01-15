package com.fdev.technogram.datasource.network.business.implementation

import com.fdev.technogram.datasource.network.NetworkErrorConst
import com.fdev.technogram.datasource.network.business.abstraction.CategoryNetworkDataSource
import com.fdev.technogram.datasource.network.framework.mapper.CategoryNetworkMapper
import com.fdev.technogram.datasource.network.framework.service.CategoryApiService
import com.fdev.technogram.model.Category
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class CategoryNetworkDataSourceImpl
@Inject
constructor(
        private val categoryApiService: CategoryApiService,
        private val categoryNetworkMapper: CategoryNetworkMapper
) : CategoryNetworkDataSource {

    override suspend fun getAllCategories(): List<Category> {
        val result = categoryApiService.getRecentNews()
        result.data?.let{ data ->
            val newList = ArrayList<Category>()
            data.forEach { category ->
                newList.add(categoryNetworkMapper.mapToDomain(category))
            }
            return newList
        } ?: throw Exception(NetworkErrorConst.DATA_NULL)
    }

}