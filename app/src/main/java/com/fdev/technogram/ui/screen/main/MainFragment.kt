package com.fdev.technogram.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdev.technogram.R
import com.fdev.technogram.databinding.FragmentMainBinding
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.util.produceFakeNewsData

class MainFragment : Fragment(){


    private var _binding : FragmentMainBinding? = null

    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState:     Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.apply {
            this.mainToolbarContent.setContent {
                TechnogramTopAppBar(onBurgerClicked = { /*TODO*/ })
            }
            this.navigationDrawerContent.setContent {
                Button(
                    onClick = {
                        val childFragment = childFragmentManager.findFragmentById(R.id.top_nav_host_fragment)
                        childFragment?.let{
                         it.findNavController().navigate(R.id.newsDetailFragment , bundleOf(MainBundleConst.HOME_TO_NEWSDETAIL_NEWS_BUNDLE to produceFakeNewsData()))
                         binding.root.closeDrawers()
                        }
                    }
                ){
                    Text("Go to fake news")
                }
            }
        }
        val view = binding.root
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}