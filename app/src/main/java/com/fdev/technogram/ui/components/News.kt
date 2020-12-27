package com.fdev.technogram.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
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
                        .fillMaxWidth()
                        .weight(7f)
                        .clip(RoundedCornerShape(5.dp))
        )
        Text(

                text = news.title,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                        .weight(3f),
                style = typography.h4
        )
        Row(
                modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
        ) {
            Text(
                    text = "by ${news.writer}",
                    style = typography.h6,
            )
            Text(
                    text = getFullDateFromLong(news.publishTime),
                    style = typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                            .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LeftImageNews(news: News, modifier: Modifier = Modifier) {

    Row(
            modifier = modifier
    ) {
        Card(
                modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .aspectRatio(1f)

        ) {
            ImageWithErrorImage(
                imageUrl = news.headerImg
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
                modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(3f)
        ) {
            Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = news.title,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.h5.merge(TextStyle(fontWeight = FontWeight.W600))
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "by ${news.writer}",
                    style = typography.h6,
            )
        }

    }

}