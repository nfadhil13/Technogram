package com.fdev.technogram.ui.screen.main.searchresult

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
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.ActivityViewModel
import com.fdev.technogram.ui.theme.TechnogramTheme
import com.fdev.technogram.ui.screen.main.MainBundleConst
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(){


    private val searchViewModel : SearchViewModel by viewModels()
    private val activityViewModel : ActivityViewModel by activityViewModels()

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
                TechnogramTheme(
                        darkTheme = activityViewModel.darkTheme
                ) {
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