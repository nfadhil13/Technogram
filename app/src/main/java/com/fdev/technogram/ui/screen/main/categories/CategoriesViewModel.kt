package com.fdev.technogram.ui.screen.main.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fdev.technogram.model.Category
import com.fdev.technogram.repository.category.CategoryInteractors

class CategoriesViewModel
@ViewModelInject
constructor(
        categoryInteractors: CategoryInteractors
) : ViewModel(){


    var categories : List<Category> by mutableStateOf(listOf())
    private set



}