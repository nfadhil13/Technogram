package com.fdev.technogram.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class ActivityViewModel
@ViewModelInject
constructor(

) : ViewModel(){

    var darkTheme : Boolean by mutableStateOf(false)

}