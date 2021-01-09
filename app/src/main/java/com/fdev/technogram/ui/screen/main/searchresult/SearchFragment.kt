package com.fdev.technogram.ui.screen.main.searchresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fdev.technogram.R
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.TechnogramTheme
import com.fdev.technogram.ui.screen.main.MainBundleConst
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(){


    private val searchViewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(MainBundleConst.SEARCH_QUERY_BUNDLE)?.let{
            searchViewModel.changeSearchQuery(it)
            searchViewModel.search()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TechnogramTheme() {
                    Search(onNewsClicked = { news -> navToDetailNews(news) }, viewModel = searchViewModel)
                }
            }
        }
    }

    private fun navToDetailNews(news: News) {
        findNavController().navigate(
                R.id.newsDetailFragment,
                bundleOf(
                        MainBundleConst.HOME_TO_NEWSDETAIL_NEWS_BUNDLE to news
                )
        )
    }

}