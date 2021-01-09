package com.fdev.technogram.datasource.network.business.abstraction

import com.fdev.technogram.model.Category

interface CategoryNetworkDataSource {

    suspend fun getAllCategories() : List<Category>

}