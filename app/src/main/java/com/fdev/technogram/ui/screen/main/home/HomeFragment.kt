package com.fdev.technogram.ui.screen.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fdev.technogram.R
import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.ActivityViewModel
import com.fdev.technogram.ui.theme.TechnogramTheme
import com.fdev.technogram.ui.screen.main.MainBundleConst
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(){

    @Inject
    lateinit var networkDataSource: NewsNetworkDataSource

    private val homeViewModel : HomeViewModel by activityViewModels()
    private val activityViewModel : ActivityViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TechnogramTheme(
                        darkTheme = activityViewModel.darkTheme
                ) {
                    Home(homeViewModel = homeViewModel , onNewsClicked = { news -> navigateToNewsDetail(news)} , darkTheme = activityViewModel.darkTheme)
                }
            }
        }
    }


    private fun navigateToNewsDetail(news: News) {
        findNavController().navigate(
                R.id.newsDetailFragment,
                 bundleOf(
                        MainBundleConst.HOME_TO_NEWSDETAIL_NEWS_BUNDLE to news
                )
        )
    }


}