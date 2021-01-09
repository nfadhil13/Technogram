package com.fdev.technogram.datasource.network.framework.mapper

import com.fdev.technogram.datasource.DomainMapper
import com.fdev.technogram.datasource.network.framework.model.CategoryDto
import com.fdev.technogram.model.Category
import com.fdev.technogram.util.DateUtil
import javax.inject.Inject

class CategoryNetworkMapper
@Inject
constructor(

) : DomainMapper<CategoryDto , Category>{

    override fun mapFromDomain(domain: Category): CategoryDto {
        return CategoryDto(
                id_kategori = domain.id,
                nama_kategori = domain.categoryName
        )
    }

    override fun mapToDomain(t: CategoryDto): Category {
        return Category(
                id = t.id_kategori,
                categoryName = t.nama_kategori
        )
    }

}