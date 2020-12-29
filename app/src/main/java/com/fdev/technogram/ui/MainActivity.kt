package com.fdev.technogram.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.fdev.technogram.datasource.network.service.NewsApiService
import com.fdev.technogram.repository.Event
import com.fdev.technogram.repository.news.NewsRepository
import com.fdev.technogram.repository.news.NewsRepositoryImpl
import com.fdev.technogram.ui.screen.TechnogramMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject lateinit var newsRepositoryImpl: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnogramMain()
        }

    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(IO).launch{
            newsRepositoryImpl.getRecentNews().collect {  event ->
                when(event){
                    is Event.OnSuccess -> {
                        println(event.message)
                        event.data.forEach{
                            println(it.title)
                        }
                    }

                    is Event.OnFailure -> {
                        println(event.message)
                    }
                }
            }
        }
    }
}
