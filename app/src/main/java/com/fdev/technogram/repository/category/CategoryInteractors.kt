package com.fdev.technogram.repository.category

import javax.inject.Inject

class CategoryInteractors
@Inject
constructor(
        private val fetchAllCategory: FetchAllCategory
)