package com.fdev.technogram.ui.screen.home


import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
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
fun Home(
        onNewsClicked : () -> Unit,
){

    ScrollableColumn(
            modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
    ){
        Text(
                text= "Popular News",
                style = typography.h2
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        HeaderNews(
                news = produceFakeNewsData(),
                modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable(onClick = onNewsClicked)
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        for(news in produceBunchFakeNews(6)){
                LeftImageNews(
                        news = news ,
                        modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 96.dp , max = 128.dp)
                )

            Spacer(modifier = Modifier.height(height = 12.dp))
        }
    }

}