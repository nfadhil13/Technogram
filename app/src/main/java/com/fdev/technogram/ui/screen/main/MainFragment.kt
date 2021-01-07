package com.fdev.technogram.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.fdev.technogram.databinding.FragmentMainBinding
import com.fdev.technogram.ui.TechnogramTheme
import com.fdev.technogram.ui.components.TechnogramDrawer
import com.fdev.technogram.ui.components.TechnogramTopAppBar

class MainFragment : Fragment(){


    private var _binding : FragmentMainBinding? = null

    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState:     Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        settinUpComposeView()
        val view = binding.root
        return view
    }


    private fun settinUpComposeView(){
        binding.apply {
            this.mainToolbarContent.setContent {
                TechnogramTheme() {
                    TechnogramTopAppBar(
                        onBurgerClicked = {  binding.root.open()},
                        onSearchClicked = {  binding.root.open()}
                    )
                }
            }
            this.navigationDrawerContent.setContent {
                TechnogramTheme() {
                    TechnogramDrawer(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        isLoggedIn = false,
                        onSignInClicked = { /*TODO*/ },
                        onSearch = { /*TODO*/ },
                        searchKey = "" ,
                        onValueChange = { /*TODO*/ })
                }
            }
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}