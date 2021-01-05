package com.fdev.technogram.ui.screen.main.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.TechnogramTheme
import com.fdev.technogram.ui.screen.main.MainBundleConst
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment(){


    private val newsDetailViewModel : NewsDetailViewModel by viewModels()
    private lateinit var currentNews : News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.get(MainBundleConst.HOME_TO_NEWSDETAIL_NEWS_BUNDLE)?.let { newsBundle ->
            if(newsBundle is News){
                currentNews = newsBundle
            }else{
                throw Exception("This bundle has to be com.fdev.technogram.model.News ")
            }
        }?: throw Exception("You need to include news bundle")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TechnogramTheme() {
                    NewsDetail(currentNews , newsDetailViewModel = newsDetailViewModel)
                }

            }
        }
    }

    fun refresh() {
        println("refresh")
    }

}