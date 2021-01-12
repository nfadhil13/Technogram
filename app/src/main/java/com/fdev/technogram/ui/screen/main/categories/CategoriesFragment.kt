package com.fdev.technogram.ui.screen.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fdev.technogram.R
import com.fdev.technogram.model.Category
import com.fdev.technogram.ui.ActivityViewModel
import com.fdev.technogram.ui.TechnogramTheme
import com.fdev.technogram.ui.screen.main.MainBundleConst
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment  : Fragment() {

    private val categoriesViewModel : CategoriesViewModel by viewModels()
    private val activityViewModel : ActivityViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                TechnogramTheme(
                        darkTheme = activityViewModel.darkTheme
                ){
                    Categories(categoriesViewModel = categoriesViewModel, onCategoryClicked = { selectedCategories -> navToSearch(selectedCategories) })
                }

            }
        }
    }

    private fun navToSearch(selectedCategories: Category) {
        findNavController()
                .navigate(R.id.action_categoriesFragment_to_searchFragment ,
                        bundleOf(MainBundleConst.SEARCH_QUERY_BUNDLE to selectedCategories.categoryName)
                )
    }


}