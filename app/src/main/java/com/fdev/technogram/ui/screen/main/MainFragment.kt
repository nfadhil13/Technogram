package com.fdev.technogram.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.TopAppBar
import androidx.core.view.isVisible
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


class MainFragment : Fragment() , SwipeRefreshInterface{


    private var _binding : FragmentMainBinding? = null

    private val binding get() = _binding!!


    private var currentOnRefresh : () -> Unit = {}



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
            setCurrentOnRefresh(it.childFragmentManager.fragments.last())
            it.childFragmentManager.addOnBackStackChangedListener {
                setCurrentOnRefresh(it.childFragmentManager.fragments.last())
            }
        }
        binding.mainSwipeRefresh.setOnRefreshListener {
            currentOnRefresh()
        }
    }



    private fun setCurrentOnRefresh(fragment : Fragment){

        currentOnRefresh = when(fragment){
            is HomeFragment -> {
                println("currently in home fragment")
                ({fragment.refresh(this)})
            }
            is NewsDetailFragment -> {
                println("currently in news fragment")
                ({fragment.refresh()})
            }
            else -> {
                println("currently in ${fragment::class.java.canonicalName} fragment")
                ({})
            }
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRefreshDone() {
        binding.mainSwipeRefresh.isRefreshing = false
    }


}