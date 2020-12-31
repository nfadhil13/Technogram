package com.fdev.technogram.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fdev.technogram.model.News
import com.fdev.technogram.util.produceFakeNewsData


//Viewmodel for every main component to interact each other
class MainViewModel @ViewModelInject
constructor(

) : ViewModel(){

    var currentNews : News by mutableStateOf(produceFakeNewsData())




}