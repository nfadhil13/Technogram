package com.fdev.technogram.ui.screen.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.fdev.technogram.model.Category
import com.fdev.technogram.repository.DataState
import com.fdev.technogram.repository.category.CategoryInteractors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment  : Fragment() {

    @Inject lateinit var categoryInteractors: CategoryInteractors

    private var categories : List<Category> by mutableStateOf(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column() {
                    categories.forEach {
                        Text(it.categoryName)
                    }
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(IO).launch{
            categoryInteractors.fetchAllCategory.fetch(IO).collect { item ->
                if(item is DataState.OnSuccess){
                    categories = item.data
                }
            }
        }
    }

}