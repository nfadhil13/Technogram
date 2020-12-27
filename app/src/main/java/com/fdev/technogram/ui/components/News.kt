package com.fdev.technogram.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.typography
import com.fdev.technogram.util.getFullDateFromLong


@Composable
fun HeaderNews(news: News, modifier: Modifier = Modifier) {
    Column(
            modifier = modifier
    ) {
        ImageWithErrorImage(
                imageUrl = news.headerImg,
                modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
        )
        Text(

                text = news.title,
                style = typography.h4
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
                modifier = Modifier
                        .fillMaxWidth()
        ) {
            Text(
                    text = "by ${news.writer}",
                    style = typography.h5,
                    modifier = Modifier
                            .align(alignment = Alignment.Bottom)
            )
            Text(
                    text = getFullDateFromLong(news.publishTime),
                    style = typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LeftImageNews(news: News, modifier: Modifier = Modifier) {
    Column(
            modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
    ) {
        ImageWithErrorImage(
                imageUrl = news.headerImg,
                modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
        )
        Text(

                text = news.title,
                style = typography.h3
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
                modifier = Modifier
                        .fillMaxWidth()
        ) {
            Text(
                    text = "by ${news.writer}",
                    style = typography.h5,
                    modifier = Modifier
                            .align(alignment = Alignment.Bottom)
            )
            Text(
                    text = getFullDateFromLong(news.publishTime),
                    style = typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .fillMaxWidth()
            )
        }
    }
}