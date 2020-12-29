package com.fdev.technogram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.fdev.technogram.datasource.network.service.NewsApiService
import com.fdev.technogram.ui.screen.TechnogramMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject lateinit var news : NewsApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnogramMain()
        }

    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(IO).launch{
            val apiResponse = news.getMostLikedNews()
            apiResponse.data?.let{
                it.forEach { news ->
                    println(news.judul)
                }
            }?: println("Null Data")
        }
    }
}
