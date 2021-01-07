package com.fdev.technogram.ui.screen.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/*
    This splash screen will be the place where we are syncing offline data of user to the server
        but not implemented yet for the sake of simplicity
 */
class SplashViewModel @ViewModelInject
constructor() : ViewModel(){

    //This is temporary , for animation before the real sync
    private val _isSplashDone  = MutableLiveData(false)

    val isSplashDone : LiveData<Boolean>
        get() = _isSplashDone


    init{
        viewModelScope.launch {
            withContext(IO){
                delay(1000)
                withContext(Main){
                    _isSplashDone.value = true
                }
            }
        }
    }

}