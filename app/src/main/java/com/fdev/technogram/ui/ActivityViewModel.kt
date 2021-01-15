package com.fdev.technogram.ui

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel
@Inject
constructor(
    private val sharedPreferences: SharedPreferences,
    private val prefEditor: SharedPreferences.Editor
) : ViewModel(){


    companion object {
        private const val USER_THEME = "com.fdev.technogra.USER_THEME"
    }


    var darkTheme : Boolean by mutableStateOf(false)
        private set


    init {
        darkTheme = sharedPreferences.getBoolean(USER_THEME , false)
    }



    fun toogleTheme(){
        darkTheme = !darkTheme
        prefEditor.putBoolean(USER_THEME , darkTheme)
        prefEditor.apply()
    }

}