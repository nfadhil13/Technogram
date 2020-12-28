package com.fdev.technogram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.fdev.technogram.ui.components.HeaderNews
import com.fdev.technogram.ui.components.RightImagePreviewNews
import com.fdev.technogram.ui.screen.TechnogramMain
import com.fdev.technogram.ui.screen.home.Home
import com.fdev.technogram.ui.screen.home.HomeViewType
import com.fdev.technogram.util.produceFakeNewsData


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnogramMain()
        }
    }
}
