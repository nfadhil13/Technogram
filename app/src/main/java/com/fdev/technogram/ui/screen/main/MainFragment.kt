package com.fdev.technogram.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.TopAppBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentOnAttachListener
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.fdev.technogram.R
import com.fdev.technogram.databinding.FragmentMainBinding
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.ui.screen.main.home.HomeFragment
import com.fdev.technogram.ui.screen.main.newsdetail.NewsDetailFragment


class MainFragment : Fragment(){


    private var _binding : FragmentMainBinding? = null

    private val binding get() = _binding!!


    private val currentOnRefresh 


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.apply {
            this.topAppBar.setContent { 
                TechnogramTopAppBar(onBurgerClicked = { /*TODO*/ })
            }
        }
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val childFragment = childFragmentManager.findFragmentById(R.id.top_nav_host_fragment)
        childFragment?.let{
            it.childFragmentManager.addOnBackStackChangedListener {
                when(val currentFragment = it.childFragmentManager.fragments.last()){
                    is HomeFragment -> {
                        println("currently in home fragment")
                        currentFragment.refresh()
                    }
                    is NewsDetailFragment -> {
                        println("currently in news fragment")
                        currentFragment.refresh()
                    }
                    else -> {
                        println("currently in ${currentFragment::class.java.canonicalName} fragment")
                    }
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}