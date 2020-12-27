package com.fdev.technogram.ui.screen.home


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fdev.technogram.ui.components.HeaderNews
import com.fdev.technogram.ui.components.LeftImageNews
import com.fdev.technogram.ui.typography
import com.fdev.technogram.util.produceBunchFakeNews
import com.fdev.technogram.util.produceFakeNewsData

@Composable
fun Home(){

    Column(
            modifier = Modifier.padding(3.dp)
    ){
        Text(
                text= "Popular News",
                style = typography.h3
        )
        HeaderNews(
                news = produceFakeNewsData() ,
                modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        for(news in produceBunchFakeNews(6)){
            LeftImageNews(news = news)
        }
    }

}