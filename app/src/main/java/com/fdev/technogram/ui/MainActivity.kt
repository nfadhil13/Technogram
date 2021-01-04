package com.fdev.technogram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import com.fdev.technogram.ui.screen.main.TechnogramMain
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(
                AmbientBackPressHandler provides backPressHandler
            ) {
                MaterialTheme() {
                    TechnogramMain()
                }
            }

        }


    }

    override fun onBackPressed() {
        if(!backPressHandler.handle()){
            super.onBackPressed()
        }
    }


}
