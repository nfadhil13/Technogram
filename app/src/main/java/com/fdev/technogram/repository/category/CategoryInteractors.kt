package com.fdev.technogram.repository.category

import javax.inject.Inject

class CategoryInteractors
@Inject
constructor(
        val fetchAllCategory: FetchAllCategory
)