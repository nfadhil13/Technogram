package com.fdev.technogram.ui.screen.main.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.technogram.model.Category
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.category.CategoryInteractors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoriesViewModel
@ViewModelInject
constructor(
        private val categoryInteractors: CategoryInteractors
) : ViewModel(){


    var categories : List<Category> by mutableStateOf(listOf())
    private set


    init {
        getAllCategories()
    }

    private fun getAllCategories(){
        viewModelScope.launch(IO){
            categoryInteractors.fetchAllCategory.fetch(Dispatchers.IO).collect { item ->
                if(item is DataState.OnSuccess){
                    categories = item.data
                }
            }
        }
    }

}