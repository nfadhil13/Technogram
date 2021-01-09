package com.fdev.technogram.datasource.network.business.implementation

import com.fdev.technogram.datasource.network.business.abstraction.CategoryNetworkDataSource
import com.fdev.technogram.datasource.network.framework.mapper.CategoryNetworkMapper
import com.fdev.technogram.datasource.network.framework.service.CategoryApiService
import com.fdev.technogram.model.Category
import javax.inject.Inject

class CategoryNetworkDataSourceImpl
@Inject
constructor(
        private val categoryApiService: CategoryApiService,
        private val categoryNetworkMapper: CategoryNetworkMapper
) : CategoryNetworkDataSource {

    override suspend fun getAllCategories(): List<Category> {
        return categoryApiService.getRecentNews().map{ it ->
            categoryNetworkMapper.mapToDomain(it)
        }
    }

}