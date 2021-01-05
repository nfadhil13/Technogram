package com.fdev.technogram.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.TopAppBar
import androidx.fragment.app.Fragment
import com.fdev.technogram.databinding.FragmentMainBinding
import com.fdev.technogram.ui.components.TechnogramTopAppBar


class MainFragment : Fragment(){


    private var _binding : FragmentMainBinding? = null

    private val binding get() = _binding!!

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}