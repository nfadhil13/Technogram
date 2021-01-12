package com.fdev.technogram.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fdev.technogram.R

class MainViewModel
@ViewModelInject
constructor(

): ViewModel(){


    var currentSelected : Int by mutableStateOf(0)
        private set


    fun changeCurrentSelected(newSelectedItem : Int){
        currentSelected = newSelectedItem
    }


}